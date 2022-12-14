package game_interface;
import javax.swing.*;
import java.util.Random;

public class Pterodactilo extends Sprite{
    private int dx;
    private int dy;
    private int speed = 1; //Aumenta conforme aumenta el nivel.
    private int timer;
    private int floor;
    private int points;

    public Pterodactilo(int floor, String dir){
        initPterodactilo(floor, dir);

    }

    private void initPterodactilo(int floor, String dir){
        this.x = 0;
        this.y = 30;
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
        var ii = new ImageIcon("images/ptero.png");
        image = ii.getImage();
    }

    public void movement(){
        if(x <= 0 || x >= Constantes.WIDTH - imageWidth){
            speed = speed * -1;
        }
        x = x + speed;
        //if(x >= Constantes.WIDTH - imageWidth){
        //  x = Constantes.WIDTH - imageWidth;
        //}
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
     *  builts a foca object in a desired floor
     */
    //public Foca(int floor, String dir){
    //this.setName("foca");
    //this.setSpritePath("resources\foca.png");
    //this.setDir(dir);
    // }

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
            System.out.println("Direci??n incorrecta");
        }
    }
}
