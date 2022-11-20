package game_interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Foca extends Sprite {
    private int dx;
    private int dy;
   // private int speed = 1; //Aumenta conforme aumenta el nivel.
    private int timer;
    private int points;

    public Foca(int floor, String dir){
        initFoca(floor, dir);

    }

    private void initFoca(int floor, String dir){
        //this.x = x;
        //this.y = y;
        //this.floor = floor;
        this.points = Constantes.FOCA_POINTS;
        this.isDestroyed = false;
        this.setName("foca");
        this.setDir(dir);
        this.setFloorC(floor);
        loadImage();
        getImageDimensions();
        //resetState();
    }

    private void loadImage(){
        var ii = new ImageIcon("images/focaleft.png");
        image = ii.getImage();
    }

    @Override
    public void movement(){
        if(x <= 0 || x >= Constantes.WIDTH - imageWidth){
            speed = speed * -1;
        }
        x = x + speed;

    }

    /**
     * Metodo para establecer posiciones iniciales.
     */
    private void resetState(){
        x = 100;
        y = 100;
    }

    /**
     * Indicates the direction of the foca
     */
    private String dir;

    /**
     *  Sets the floor and Y coords of the foca
     * @param floor int range 1 - 15
     */

    public void setFloorC(int floor) {
        this.setFloor(floor);

        // revisar coordenadas
        switch (floor) {
            case 1, 5, 9, 13 -> this.setY(549);
            case 2, 6, 10, 14 -> this.setY(404);
            case 3, 7, 11, 15 -> this.setY(259);
            case 4, 8, 12 -> this.setY(114);
        }

    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
        if (dir.equals("DI")){
            this.setX(738); // ver cual es el ancho de la ventana
        } else if (dir.equals("ID")){
            this.setX(1);
        } else{
            System.out.println("Direción incorrecta");
        }
    }

//    /**
//     * Sets new speed.
//     *
//     * @param speed New value of speed.
//     */
//    public void setSpeed(int speed) {
//        this.speed = speed;
//    }
//
//    /**
//     * Gets speed.
//     *
//     * @return Value of speed.
//     */
//    public int getSpeed() {
//        return speed;
//    }
}
