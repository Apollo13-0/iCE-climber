package game_interface;

import javax.swing.*;
import java.util.Random;

public class Hielo extends Sprite{
   // private int speed = 1; //Aumenta conforme aumenta el nivel.
    private int timer;
    private int points;
    private int xR;


    public Hielo(int floor){
        initHielo(floor);
    }

    private void initHielo(int floor){

        Random num = new Random();
        this.xR = num.nextInt(780);
        this.x = xR;
        this.points = Constantes.HIELO_POINTS;
        this.isDestroyed = false;
        this.setName("hielo");
        this.setFloorC(floor);
        loadImage();
        getImageDimensions();

    }

    private void loadImage(){
        var ii = new ImageIcon("images/hielo.png");
        image = ii.getImage();
    }


    public void movement(){
        if(y <= 0 ){
            speed = speed * -1;
        }
        if (y >= Constantes.HEIGHT - imageHeight){
            this.isDestroyed = true;
        }
        y = y + speed;
    }

    /**
     * Metodo para establecer posiciones iniciales.
     */
    private void resetState(){
        x = 100;
        y = 100;
    }

    /**
     *  Sets the floor and Y coords of the foca
     * @param floor int range 1 - 15
     */

    public void setFloorC(int floor) {
        this.setFloor(floor);

        // revisar coordenadas
        switch (floor){
            case 1, 5, 9, 13 -> this.setY(549);
            case 2, 6, 10, 14 -> this.setY(404);
            case 3, 7, 11, 15 -> this.setY(259);
            case 4, 8, 12 -> this.setY(114);
        }

    }
//
//    /**
//     * Gets speed.
//     *
//     * @return Value of speed.
//     */
//    public int getSpeed() {
//        return speed;
//    }
//
//    /**
//     * Sets new speed.
//     *
//     * @param speed New value of speed.
//     */
//    public void setSpeed(int speed) {
//        this.speed = speed;
//    }
}
