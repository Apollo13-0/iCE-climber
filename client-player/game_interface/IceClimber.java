package game_interface;

import client.clientLogic;
import game_interface.Board;

import javax.swing.JFrame;

public class IceClimber extends JFrame {
    public IceClimber(String tipoJuego, clientLogic client){
        initGUI(tipoJuego, client);
    }

    private void initGUI(String tipoJuego, clientLogic client){
        add(new Board(tipoJuego, client));
        setTitle("Ice Climbers Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        setResizable(false);
        //this.getContentPane().setBackground(Color.BLACK);
        pack();
    }
}
