package game_interface;

import game_interface.Board;

import javax.swing.JFrame;

public class IceClimber extends JFrame {
    public IceClimber(){
        initGUI();
    }

    private void initGUI(){
        add(new Board());
        setTitle("Ice Climbers Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        //this.getContentPane().setBackground(Color.BLACK);
        pack();
    }
}
