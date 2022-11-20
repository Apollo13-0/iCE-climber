package game_interface;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game_interface.IceClimber;

public class LaunchPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton myButton = new JButton("Jugador 1");
    JButton myButton2 = new JButton("Jugador 2");
    JButton myButton3 = new JButton("Espectador");

    public LaunchPage(){

        myButton.setBounds(100, 100, 200, 40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        myButton2.setBounds(100, 150, 200, 40);
        myButton2.setFocusable(false);
        myButton2.addActionListener(this);
        myButton3.setBounds(100, 200, 200, 40);
        myButton3.setFocusable(false);
        myButton3.addActionListener(this);

        frame.add(myButton);
        frame.add(myButton2);
        frame.add(myButton3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==myButton){
            frame.dispose();
            var game = new IceClimber("Single");
            game.setVisible(true);
        }
        if(e.getSource()==myButton2){
            frame.dispose();
            var game = new IceClimber("Coop");
            game.setVisible(true);
        }


    }
}
