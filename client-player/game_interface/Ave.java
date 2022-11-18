package game_interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.*;
import java.util.Random;

public class Ave extends Sprite {
    private int dx;
    private int dy;
    private int speed = 1; //Aumenta conforme aumenta el nivel.
    private int timer;
    private boolean hit;
    private int floor;
    private int points;
    private boolean booleandir;
    private int n1;
    private int n2;

    public Ave(int floor){
        initAve(floor);
    }

    private void initAve(int floor){

        this.points = Constantes.AVE_POINTS;

        Random booleandir = new Random();
        Random num = new Random();
        this.booleandir = booleandir.nextBoolean();
        this.n1 = num.nextInt(400);
        this.n2 = num.nextInt(150);

        hit = false;

        this.setName("ave");
        this.setDir();
        this.setFloorC(floor);
        loadImage();
        getImageDimensions();

    }

    private void loadImage(){
        var ii = new ImageIcon("images/ave.png");
        image = ii.getImage();
    }

    public void movement(){

        if(x <= 0 || x >= Constantes.WIDTH - imageWidth){
            speed = speed * -1;
        }
        x = x + speed;

        if(x >= this.n1 && x <= (this.n2 + this.n1)){
            if(y <= 0 || y >= Constantes.HEIGHT- imageHeight){
                speed = speed * -1;
            }
            if(this.booleandir){
                y = y - speed;
            }else{
                y = y + speed;
            }
        }
    }

    /**
     * Metodo para establecer posiciones iniciales.
     */
    private void resetState(){
        x = 100;
        y = 100;
    }

    /**
     * Indicates the direction of the ave
     */
    private String dir;

    /**
     *  Sets the floor and Y coords of the ave
     * @param floor int range 1 - 15
     */

    public void setFloorC(int floor) {
        this.setFloor(floor);

        switch (floor){
            case 1, 5, 9, 13 -> this.setY(531);
            case 2, 6, 10, 14 -> this.setY(386);
            case 3, 7, 11, 15 -> this.setY(241);
            case 4, 8, 12 -> this.setY(96);
        }

    }

    /**
     * gets the diretion
     * @return String
     */
    public String getDir() {
        return dir;
    }

    /**
     * Sets the direction
     *
     */
    public void setDir() {
        this.setX(1);
    }
}
