package game_interface;

import javax.swing.*;

public class Verduras extends Sprite{
    //private boolean taken;
    private int points;
    private int tipo;
    private String verduraType;

    public Verduras(int x, int y, int tipo){
        initVerduras(x, y, tipo);
    }

    private void initVerduras(int x, int y, int tipo){
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.isDestroyed = false;
        //taken = false;
        loadImage();
        getImageDimensions();
    }
    private void loadImage(){
        switch (tipo){
            case 0:
                var ii = new ImageIcon("images/block.png"); //imagen bloque
                image = ii.getImage();
                verduraType = "BERENJENA";
                break;
            case 1:
                ii = new ImageIcon("images/block.png"); //imagen bloque
                image = ii.getImage();
                verduraType = "LECHUGA";
                break;
            case 2:
                ii = new ImageIcon("images/block.png"); //imagen bloque
                image = ii.getImage();
                verduraType = "BANANO";
                break;
            case 3:
                ii = new ImageIcon("images/block.png"); //imagen bloque
                image = ii.getImage();
                verduraType = "NARANJA";
                break;

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
