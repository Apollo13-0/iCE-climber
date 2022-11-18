package game_interface;

import javax.swing.*;
import java.awt.Image;
import java.awt.Rectangle;


public class Sprite {

    /**
     *  Coord in x
     */
    int x;

    /**
     *  Coord in y
     */
    int y;
    int imageHeight;
    int imageWidth;
    Image image;

    /**
     *  Name of the enemy
     */
    String name;

    /**
     * Indicates the current floor of the foca
     */
    private int floor;

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return y;
    }

    public int getImageHeight(){
        return imageHeight;
    }

    public int getImageWidth(){
        return imageWidth;
    }

    Image getImage(){
        return image;
    }

    Rectangle getRectangle(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    void getImageDimensions(){
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets Indicates the current floor of the foca.
     *
     * @return Value of Indicates the current floor of the foca.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets new Indicates the current floor of the foca.
     *
     * @param floor New value of Indicates the current floor of the foca.
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }
}
