package game_interface;

import javax.swing.*;
import java.util.Random;

public class Verduras extends Sprite{
    //private boolean taken;
    private int points;
    private int tipo;
    private String verduraType;

    public Verduras(String verduraType){
        initVerduras(verduraType);
    }

    private void initVerduras(String verduraType){

        Random num = new Random();
        this.x = num.nextInt(780);
        setFloor(num.nextInt(3)+1);

        this.verduraType = verduraType;
        this.isDestroyed = false;
        //taken = false;
        loadImage();
        getImageDimensions();
    }
    private void loadImage(){

        switch (verduraType){
            case "banano":
                var ii = new ImageIcon("images/banano.png");
                image = ii.getImage();
                this.points = Constantes.BANANO_POINTS;
                break;
            case "berenjena":
                ii = new ImageIcon("images/berenjena.png");
                image = ii.getImage();
                this.points = Constantes.BERENJENA_POINTS;
                break;
            case "lechuga":
                ii = new ImageIcon("images/lechuga.png");
                image = ii.getImage();
                this.points = Constantes.LECHUGA_POINTS;
                break;
            case "naranja":
                ii = new ImageIcon("images/naranja.png");
                image = ii.getImage();
                this.points = Constantes.NARANJA_POINTS;
                break;

        }
    }

    /**
     *  Sets the floor and Y coords of the foca
     * @param floor int range 1 - 15
     */

    public void setFloor(int floor) {

        switch (floor){
            case 1 -> this.setY(549);
            case 2 -> this.setY(404);
            case 3 -> this.setY(259);
            case 4 -> this.setY(114);
        }

    }

//    boolean isTaken(){
//        return taken;
//    }
//
//    void setTaken(boolean val){
//        taken = val;
//    }

}
