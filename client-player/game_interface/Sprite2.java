package game_interface;

import java.awt.*;


public class Sprite2 {

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

    public int getImageBottom() {
        return imageBottom;
    }

    public int getImageTop() {
        return imageTop;
    }

    int imageBottom;
    int imageTop;


    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    boolean isDestroyed;

    /**
     *  Name of the player/enemy
     */
    String name;

    /**
     * Indicates the current floor of the sprite
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


    public void movement(){};
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
     * Gets Indicates the current floor of the player/enemy.
     *
     * @return Value of Indicates the current floor of the player/enemy.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets new Indicates the current floor of the player/enemy.
     *
     * @param floor New value of Indicates the current floor of the player/enemy.
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }
}
