package game_interface;

import game_interface.Board;

import javax.swing.JFrame;

public class IceClimber extends JFrame {
    public IceClimber(String tipoJuego){
        initGUI(tipoJuego);
    }

    private void initGUI(String tipoJuego){
        add(new Board(tipoJuego));
        setTitle("Ice Climbers Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        //this.getContentPane().setBackground(Color.BLACK);
        pack();
    }
}
