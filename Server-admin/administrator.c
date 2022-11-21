
//Handle multiple socket connections with select and fd_set on Linux
#include <stdio.h>
#include <string.h> //strlen
#include <stdlib.h>
#include <errno.h>
#include <unistd.h> //close
#include <arpa/inet.h> //close
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <sys/time.h> //FD_SET, FD_ISSET, FD_ZERO macros
#include "constants.h"
#include <pthread.h>
#include <json-c/json.h>

/**
 * @brief containts the data of each game client
 * 
 */
typedef struct game {
	int game_num;
	int sock_descriptor;
	int viewers[MAX_VIEWERS_P_GAME];
	int viewer_num;
}game;

/**
 * @brief global variables
 * 
 */
int opt = TRUE;
int master_socket , addrlen , new_socket , client_socket[MAX_GAMES+MAX_VIEWERS_P_GAME], activity, i , valread , sd;
int max_sd;
int game_num = 0;
struct sockaddr_in address;
struct game games[MAX_GAMES];
char buffer[1025]; //data buffer of 1K
char buffer2[1025];

int buff_counter = 0;
	
//set of socket descriptors
fd_set readfds;
	
//a message
char *message = "ECHO Daemon v1.0 \r\n";
int running = 1;



/**
 * @brief sends data received by games to its viewers
 * 
 * @param data 
 * @param sd 
 */
void send_data_to_viewers(const char *data, int sd){
	int s_n;
	for (s_n = 0; s_n<game_num;s_n++){
		if(games[s_n].sock_descriptor == sd){
			break;
		}
	}
	int i;
	for(i = 0; i<games[s_n].viewer_num;i++){
		
		printf("%s\n", data);
		
		uint32_t  size = htonl(strlen(data));
		send(sd,&size, sizeof(uint32_t),0);
		send(sd, data, strlen(data)+1,0);
		send(games[s_n].viewers[i], data, strlen(data), 0);
	}
}

/**
 * @brief creates a json string to respond to a request to join as a game or viewer
 * 
 * @param answer 
 * @return const char* 
 */
const char *response_to_request(int answer){
	struct json_object *response_to_request = json_object_new_object();
	json_object_object_add(response_to_request, "tipo", json_object_new_string("Respuesta solicitud"));
	json_object_object_add(response_to_request, "feedback", json_object_new_int(answer));
	const char *feedback_string = json_object_to_json_string(response_to_request);
	return feedback_string;
}

const char *enemy_msg_string(char *cl_instruction){
	//ejemplo de mensaje: 1-2-foca-DI

	char *cl_instruction_tken = strtok(cl_instruction, "-\n");
	struct json_object *enemy_json = json_object_new_object();
	strtok(cl_instruction_tken, NULL);
	json_object_object_add(enemy_json, "tipo", json_object_new_string("enemigo"));
	json_object_object_add(enemy_json, "piso", json_object_new_int(atoi(cl_instruction_tken)));
	strtok(cl_instruction_tken, NULL);
	json_object_object_add(enemy_json, "nombre", json_object_new_string(cl_instruction_tken));
	strtok(cl_instruction_tken, NULL);
	json_object_object_add(enemy_json, "direccion", json_object_new_string(cl_instruction_tken));
	strtok(cl_instruction_tken, NULL);
	const char *enemy_json_string = json_object_to_json_string(enemy_json);
	return enemy_json_string;
}


/**
 * @brief checks the input of the command line. If it is a message to create an enemy or fruit it sends it to the respective game
 * 
 * @return void* 
 */
void *check_cli(){
	char inpt[256];
	while(running == TRUE){
		scanf("%s", &inpt);
		if (inpt == "exit\n"){
			running = FALSE;
		}
		else
		{

			if((inpt[0]-'0') <= MAX_GAMES){
				const char *item = enemy_msg_string(inpt+1);
				uint32_t size = htonl(strlen(item));
				send(games[(inpt[0]-'0')-1].sock_descriptor, &size, sizeof(uint32_t),0);
				send(games[(inpt[0]-'0')-1].sock_descriptor, item, strlen(item)+1, 0);
			}
			
		}
	}	

}

void *check_incoming_clients(){
	printf("Entered thread for incoming clients...\n");
	while(running==TRUE)
	{
		//clear the socket set
		FD_ZERO(&readfds);
	
		//add master socket to set
		FD_SET(master_socket, &readfds);
		max_sd = master_socket;
			
		//add child sockets to set
		for ( i = 0 ; i < MAX_GAMES+MAX_VIEWERS_P_GAME ; i++)
		{
			//socket descriptor
			sd = client_socket[i];
				
			//if valid socket descriptor then add to read list
			if(sd > 0)
				FD_SET( sd , &readfds);
				
			//highest file descriptor number, need it for the select function
			if(sd > max_sd)
				max_sd = sd;
		}
	
		//wait for an activity on one of the sockets , timeout is NULL ,
		//so wait indefinitely
		activity = select( max_sd + 1 , &readfds , NULL , NULL , NULL);
	
		if ((activity < 0) && (errno!=EINTR))
		{
			printf("select error");
		}
			
		//If something happened on the master socket ,
		//then its an incoming connection
		if (FD_ISSET(master_socket, &readfds))
		{
			if ((new_socket = accept(master_socket,
					(struct sockaddr *)&address, (socklen_t*)&addrlen))<0)
			{
				perror("accept");
				exit(EXIT_FAILURE);
			}
			
			//inform user of socket number - used in send and receive commands
			printf("New connection , socket fd is %d , ip is : %s , port : %d\n" , 
					new_socket , inet_ntoa(address.sin_addr) , ntohs(address.sin_port));
		    
			//send new connection greeting message
			//if( send(new_socket, message, strlen(message), 0) != strlen(message) )
			//{
			//	perror("send");
			//}
			
			//puts("Welcome message sent successfully");
				
			//add new socket to array of sockets
			for (i = 0; i < MAX_GAMES+MAX_VIEWERS_P_GAME; i++)
			{
				//if position is empty
				if( client_socket[i] == 0 )
				{
					client_socket[i] = new_socket;
					printf("Adding to list of sockets as %d\n" , i);
						
					break;
				}
			}
		}
			
		//else its some IO operation on some other socket
		for (i = 0; i < MAX_GAMES+MAX_VIEWERS_P_GAME; i++)
		{
			sd = client_socket[i];
				
			if (FD_ISSET( sd , &readfds))
			{
				//Check if it was for closing , and also read the
				//incoming message
				if ((valread = read( sd , buffer, 1024)) == 0)
				{
					//Somebody disconnected , get his details and print
					getpeername(sd , (struct sockaddr*)&address , \
						(socklen_t*)&addrlen);
					printf("Host disconnected , ip %s , port %d \n" ,
						inet_ntoa(address.sin_addr) , ntohs(address.sin_port));
						
					//Close the socket and mark as 0 in list for reuse
					close( sd );
					client_socket[i] = 0;
				}
					
				
				else
				{
					//set the string terminating NULL byte on the end
					//of the data read
					buffer[valread] = '\0';
					buff_counter = buff_counter+strlen(buffer);
					//printf("buffer %s\n", buffer);
					strcat(buffer2, buffer);
					if(buffer2[strlen(buffer2)-1] == '}'){
						//json parsing
						buffer2[buff_counter+1]='\0';
						printf("el mensaje es: %s\n", buffer2);
						struct json_object *parsed_json;
						struct json_object *tipo;
						const char* mensaje = buffer2;
						parsed_json = json_tokener_parse(mensaje);
						json_object_object_get_ex(parsed_json,"tipo",&tipo);

						const char* msg_type = json_object_get_string(tipo);
						printf("tipo de mensaje: %s\n", msg_type);
						//solicitud de juego
						if (strcmp(msg_type, "solicitud juego")==0){
							//revisa si la cantidad maxima de juegos no ha sido alcanzada
							printf("solicitud  de juego recibida\n");
							if (game_num<MAX_GAMES){
								printf("Solicitud aprobada\n");
								games[game_num].game_num = game_num;
								games[game_num].sock_descriptor = sd;
								game_num++;
								//sends aproval and game number
								
								const char* response_msg = response_to_request(game_num);
								printf("%s\n", response_msg);
							
								uint32_t  size = htonl(strlen(response_msg));
								send(sd,&size, sizeof(uint32_t),0);
								send(sd, response_msg, strlen(response_msg)+1,0);
								
								
							}
							else
							{

								//sends dissaproval
								const char* response_msg = response_to_request(REQUEST_DENIED);
								printf("%s\n", response_msg);
								printf("%d\n", strlen(response_msg));
								uint32_t  size = htonl(strlen(response_msg));
								send(sd,&size, sizeof(uint32_t),0);
								send(sd, response_msg, strlen(response_msg)+1,0);
								
								

							}
							
						}
						else if (strcmp(msg_type, "update")==0){
							printf("Update received\n");
							struct json_object *json_ID;
							json_object_object_get_ex(parsed_json,"ID", &json_ID);
							int id_int = json_object_get_int(json_ID)-1;
							uint32_t  size = htonl(2);
							send(sd,&size, sizeof(uint32_t),0);
							send(sd, "ok", 4, 0);
							if(id_int<=MAX_GAMES){
								send_data_to_viewers(mensaje, games[id_int].sock_descriptor);
							}
							
						}
						
						
						memset(buffer2,0,1025*sizeof(char));
						buff_counter=0;
						
					}
					printf("%s", buffer);
					
					
					

				}
				
						
			}
			
			
			
		}
	}

}

int main(int argc , char *argv[])
{
	
	//initialise all client_socket[] to 0 so not checked
	for (i = 0; i < MAX_GAMES+MAX_VIEWERS_P_GAME; i++)
	{
		client_socket[i] = 0;
	}

	for (i = 0; i < MAX_GAMES;i++){
		games[i].game_num = 0;
		games[i].sock_descriptor = 0;
		games[i].viewers[0] = 0;
		games[i].viewers[1] = 0;
		games[i].viewer_num = 0;
	}	
	//create a master socket
	if( (master_socket = socket(AF_INET , SOCK_STREAM , 0)) == 0)
	{
		perror("socket failed");
		exit(EXIT_FAILURE);
	}
	
	//set master socket to allow multiple connections ,
	//this is just a good habit, it will work without this
	if( setsockopt(master_socket, SOL_SOCKET, SO_REUSEADDR, (char *)&opt,
		sizeof(opt)) < 0 )
	{
		perror("setsockopt");
		exit(EXIT_FAILURE);
	}
	
	//type of socket created
	address.sin_family = AF_INET;
	address.sin_addr.s_addr = inet_addr(DEFAULT_IP);
	address.sin_port = htons( PORT );
		
	//bind the socket to localhost port 8888
	if (bind(master_socket, (struct sockaddr *)&address, sizeof(address))<0)
	{
		perror("bind failed");
		exit(EXIT_FAILURE);
	}
	printf("Listener on port %d \n", PORT);
		
	//try to specify maximum of 3 pending connections for the master socket
	if (listen(master_socket, 3) < 0)
	{
		perror("listen");
		exit(EXIT_FAILURE);
	}
		
	//accept the incoming connection
	addrlen = sizeof(address);
	puts("Waiting for connections ...");

	/**
	 * @brief threads to check incoming clients and to check cli instruction
	 * 
	 */
	pthread_t check_clients, check_commands;
	pthread_create(&check_clients, NULL, check_incoming_clients, NULL);
	pthread_join(check_clients, NULL);
	pthread_create(&check_commands, NULL, check_cli, NULL);
	pthread_join(check_clients, NULL);

	return 0;
}
