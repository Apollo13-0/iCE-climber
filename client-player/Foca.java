import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Foca extends Sprite{
    private int dx;
    private int dy;
    private int speed = 1; //Aumenta conforme aumenta el nivel.

    public Foca(){

    }

    /**
     * Indicates the direction of the foca
     */
    private String dir;

    /**
     *  builts a foca object in a desired floor
     */
    public Foca(int floor, String dir){
        this.setName("foca");
        //this.setSpritePath("resources\foca.png");
        this.setDir(dir);
    }

    /**
     *  Sets the floor and Y coords of the foca
     * @param floor int range 1 - 15
     */

    public void setFloorC(int floor) {
        this.setFloor(floor);

        // revisar coordenadas
        switch (floor){
            case 1, 5, 9, 13 -> this.setY(700);
            case 2, 6, 10, 14 -> this.setY(525);
            case 3, 7, 11, 15 -> this.setY(350);
            case 4, 8, 12 -> this.setY(175);
        }

    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
        if (dir.equals("DI")){
            this.setX(300); // ver cual es el ancho de la ventana
        } else if (dir.equals("ID")){
            this.setX(0);
        } else{
            System.out.println("Direción incorrecta");
        }
    }
}
