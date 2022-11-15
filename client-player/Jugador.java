import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Jugador extends Sprite{

    /**
     *  Identifier for each player
     */
    private int ID;

    /**
     *  Coord in x
     */
    private int xCoord;

    /**
     *  Coord in y
     */
    private int yCoord;

    private int dx;
    private int dy;

    /**
     * Number of player lifes
     */
    private int vidas;

    private String tipoJugador;//Popo o Nana

    public Jugador(String tipoJugador){
        this.tipoJugador = tipoJugador;
        initJugador();
    }

    private void initJugador(){
        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage(){
        if(tipoJugador.equals("Popo")){
            var ii = new ImageIcon("images/poporun.png"); //imagen nana
            image = ii.getImage();
            //ImageIcon ii = new ImageIcon("images/poporun.png");
            //Image scaleImage = ii.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        }
        else{
            var ii = new ImageIcon("images/poporun.png"); //imagen nana
            image = ii.getImage();
        }
    }

    public void movement(){
        x += dx;
        y -= dy;

        if(x <= 0){
            x = 0;
        }
        if(x >= Constantes.WIDTH - imageWidth){
            x = Constantes.WIDTH - imageWidth;
        }
        //if(y >= Constantes.HEIGHT - imageHeight){ //Hay que revisar como funcionan los limites. Si se construye de arriba hacia abajo o vicerversa.
           // y = Constantes.HEIGHT - imageHeight;
       // }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(tipoJugador.equals("Popo")){
            if(key == KeyEvent.VK_LEFT){
                dx = -3;
            }
            if(key == KeyEvent.VK_RIGHT){
                dx = 3;
            }
            if(key == KeyEvent.VK_UP){ //hay que revisar tambien.
                dy = 2;
            }
        }else{if(key == KeyEvent.VK_A){
            dx = -3;
        }
            if(key == KeyEvent.VK_D){
                dx = 3;
            }
            if(key == KeyEvent.VK_W){ //hay que revisar tambien.
                dy = 2;
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(tipoJugador.equals("Popo")){
            if(key == KeyEvent.VK_LEFT){
                dx = 0;
            }
            if(key == KeyEvent.VK_RIGHT){
                dx = 0;
            }
            if (key == KeyEvent.VK_UP){
                dy = 0;
            }
        }else{
            if(key == KeyEvent.VK_A){
                dx = 0;
            }
            if(key == KeyEvent.VK_D){
                dx = 0;
            }
            if (key == KeyEvent.VK_W){
                dy = 0;
            }
        }
    }

    private void resetState(){
        x = Constantes.INIT_JUGADOR_X;
        y = Constantes.INIT_JUGADOR_Y;
        vidas = Constantes.LIVES_JUGADOR;
    }

    /**
     * Gets Number of player lifes.
     *
     * @return Value of Number of player lifes.
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * Sets new Number of player lifes.
     *
     * @param vidas New value of Number of player lifes.
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     * Gets Coord in y.
     *
     * @return Value of Coord in y.
     */
    public int getYCoord() {
        return yCoord;
    }

    /**
     * Sets new tipoJugador.
     *
     * @param tipoJugador New value of tipoJugador.
     */
    public void setTipoJugador(String tipoJugador) {
        this.tipoJugador = tipoJugador;
    }

    /**
     * Sets new Coord in y.
     *
     * @param yCoord New value of Coord in y.
     */
    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Gets tipoJugador.
     *
     * @return Value of tipoJugador.
     */
    public String getTipoJugador() {
        return tipoJugador;
    }

    /**
     * Gets Coord in x.
     *
     * @return Value of Coord in x.
     */
    public int getXCoord() {
        return xCoord;
    }

    /**
     * Sets new Coord in x.
     *
     * @param xCoord New value of Coord in x.
     */
    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Gets Identifier for each player.
     *
     * @return Value of Identifier for each player.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets new Identifier for each player.
     *
     * @param ID New value of Identifier for each player.
     */
    public void setID(int ID) {
        this.ID = ID;
    }
}
