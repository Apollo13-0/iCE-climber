import javax.swing.*;
import java.awt.Image;
import java.awt.Rectangle;


public class Sprite {

    int x;
    int y;
    int imageHeight;
    int imageWidth;
    Image image;

    protected void setX(int x){
        this.x = x;
    }

    int getX(){
        return x;
    }

    protected void setY(int y){
        this.y = y;
    }

    int getY(){
        return y;
    }

    int getImageHeight(){
        return imageHeight;
    }

    int getImageWidth(){
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

}
