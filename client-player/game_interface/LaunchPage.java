package game_interface;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.clientLogic;
import client.serverInfo;
import client.updateInfo;
import game_interface.IceClimber;
import com.google.gson.Gson;

public class LaunchPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton myButton = new JButton("Jugador 1 partida 1");
    JButton myButton2 = new JButton("Jugador 2 partida 1");
    JButton myButton3 = new JButton("Espectador partida 1");

    JButton myButton4 = new JButton("Jugador 1 partida 2");
    JButton myButton5 = new JButton("Jugador 2 partida 2");
    JButton myButton6 = new JButton("Espectador partida 2");

    client.serverInfo serverInfo = new serverInfo();
    client.updateInfo updateInfo;

    public LaunchPage(){

        myButton.setBounds(100, 10, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        myButton2.setBounds(100, 70, 200, 40);
        myButton2.setFocusable(false);
        myButton2.addActionListener(this);
        myButton3.setBounds(100, 130, 200, 40);
        myButton3.setFocusable(false);
        myButton3.addActionListener(this);

        frame.add(myButton);
        frame.add(myButton2);
        frame.add(myButton3);

        myButton4.setBounds(100, 190, 200, 40);
        myButton4.setFocusable(false);
        myButton4.addActionListener(this);
        myButton5.setBounds(100, 250, 200, 40);
        myButton5.setFocusable(false);
        myButton5.addActionListener(this);
        myButton6.setBounds(100, 310, 200, 40);
        myButton6.setFocusable(false);
        myButton6.addActionListener(this);

        frame.add(myButton4);
        frame.add(myButton5);
        frame.add(myButton6);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        // ----------------------------------------aqui falta implementar la creacion de 2 clientes------------------------------------

        // 1 jugador partida 1
        if(e.getSource()==myButton){

            clientLogic client = new clientLogic(6666, "127.0.0.1");
            client.writeSocket("{\"tipo\":\"solicitud juego\"}");
            //frame.dispose();

            Gson gson = new Gson();
            serverInfo = gson.fromJson(client.readSockect(), serverInfo.class);

             if (serverInfo.getFeedback() == 1 || serverInfo.getFeedback() == 2 ){

                 client.setID(serverInfo.getFeedback());
                 var game = new IceClimber("Single", client);
                 game.setVisible(true);
             } else{
                 System.out.println("maximo de jugadores");
             }
        }
        if(e.getSource()==myButton2){

            clientLogic client = new clientLogic(6666, "127.0.0.1");
            client.writeSocket("{\"tipo\":\"solicitud juego\"}");
            //frame.dispose();

            Gson gson = new Gson();
            serverInfo = gson.fromJson(client.readSockect(), serverInfo.class);

            if (serverInfo.getFeedback() == 1 || serverInfo.getFeedback() == 2 ){
                client.setID(serverInfo.getFeedback());
                var game = new IceClimber("Coop", client);
                game.setVisible(true);
            } else{
                System.out.println("maximo de jugadores");
            }

        }
        if(e.getSource()==myButton3){
//----------------------------- AQUI VA LOGICA DE OBSERVADOR ------------------------------------------
//            clientLogic client = new clientLogic(6666, "127.0.0.1");
//            client.writeSocket("{\"tipo\":\"solicitud juego\"}");
//            frame.dispose();
//
//            Gson gson = new Gson();
//            serverInfo = gson.fromJson(client.readSockect(), serverInfo.class);
//
//            if (serverInfo.getFeedback() == 1 || serverInfo.getFeedback() == 2 ){
//                client.setID(serverInfo.getFeedback());
//                var game = new IceClimber("Coop", client);
//                game.setVisible(true);
//            } else{
//                System.out.println("maximo de jugadores");
//            }

        }


    }
}
