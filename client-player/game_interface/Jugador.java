package game_interface;

import game_interface.Sprite;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Jugador extends Sprite {

    /**
     *  Identifier for each player
     */
    private int ID;


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
            var ii = new ImageIcon("images/popostand.png"); //imagen nana
            image = ii.getImage();
            //ImageIcon ii = new ImageIcon("images/poporun.png");
            //Image scaleImage = ii.getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT);
        }
        else{
            var ii = new ImageIcon("images/poporunright.png"); //imagen nana
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
        //if(y >= game_interface.Constantes.HEIGHT - imageHeight){ //Hay que revisar como funcionan los limites. Si se construye de arriba hacia abajo o vicerversa.
           // y = game_interface.Constantes.HEIGHT - imageHeight;
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
     * Sets new tipoJugador.
     *
     * @param tipoJugador New value of tipoJugador.
     */
    public void setTipoJugador(String tipoJugador) {
        this.tipoJugador = tipoJugador;
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
