package client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import com.google.gson.Gson;


/**
 * This class handles the client connection.
 */
public class clientLogic {
    /**
     *  This is the port number, which begins at 1024.
     */
    private int port;

    private int ID;

    /**
     * String containing the IP of the computer that the client is going connect.
     */
    private String IPserver;

    /**
     * This is the socket that is used to transfer information between computers.
     */
    private Socket clientSocket;

    /**
     * This lets the aplication read primitive Java data types.
     */
    private DataInputStream inputSocketInD;

    /**
     * This lets tje application write primitive Java data type.
     */
    private DataOutputStream clientOutD;


    /**
     * This is the constructor, it finds the server and connects to it. AGREGAR LOGICA SI ES JUGADOR O OBSERVADOR
     *
     * @param newPort int.
     * @param newIPserver String.
     */
    public clientLogic(int newPort, String newIPserver) {
        this.port = newPort;
        this.IPserver = newIPserver;
        try {
            this.clientSocket = new Socket(this.IPserver, this.port);
            this.inputSocketInD = new DataInputStream(this.clientSocket.getInputStream());
            this.clientOutD = new DataOutputStream(this.clientSocket.getOutputStream());
        } catch (UnknownHostException e) {
            closeConexion();
        } catch (Exception e) {
            closeConexion();
        }
    }


    /**
     * This method returns the port number.
     *
     * @return Value of port.
     */
    public int getPort() {
        return this.port;
    }

    /**
     * This method returns the IP of the server.
     *
     * @return value of IPserver.
     */
    public String getIPserver() {
        return this.IPserver;
    }

    /**
     * This method connect to the server and sends and reads json files.
     *
     * @param jacksonStr New jackson String.
     */
    public void writeSocket(String jacksonStr) {
        try {
            this.clientOutD.writeUTF(jacksonStr); // Envia json
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  este metodo no esta funcionando
     */
    public String readSockect() {
        try {
            String message = this.inputSocketInD.readUTF();
            return  message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method closes the connection between the server and the client.
     */
    public void closeConexion(){
        try {
            this.clientSocket.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * gets ID
     * @return int
     */
    public int getID() {
        return ID;
    }

    /**
     * sets id
     * @param ID int
     */
    public void setID(int ID) {
        this.ID = ID;
    }
}