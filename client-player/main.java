import game_interface.IceClimber;
import game_interface.LaunchPage;

import java.awt.*;

public class main extends Thread {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            LaunchPage launchPage = new LaunchPage();
            //var game = new IceClimber();
            //game.setVisible(true);
        });
    }
}
