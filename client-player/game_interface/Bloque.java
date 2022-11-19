package game_interface;

import javax.swing.ImageIcon;
import java.awt.*;

public class Bloque extends Sprite{

    //private boolean destroyed;

    public Bloque(int x, int y){
        initBloque(x, y);
    }

    private void initBloque(int x, int y){
        this.x = x;
        this.y = y;
        this.isDestroyed = false;
        loadImage();
        getImageDimensions();
    }

    private void loadImage(){
        var ii = new ImageIcon("images/block.png"); //imagen bloque
        image = ii.getImage();
    }

    //boolean isDestroyed(){
        //return destroyed;
    //}

    //void setDestroyed(boolean val){
       // destroyed = val;
    //}

    //public String getBrickCoordinates(){
        //System.out.println();
    //}



}
