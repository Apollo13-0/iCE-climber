import game_interface.IceClimber;

import java.awt.*;

public class main extends Thread {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            var game = new IceClimber();
            game.setVisible(true);
        });
    }
}
