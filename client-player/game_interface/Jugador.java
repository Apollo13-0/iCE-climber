package game_interface;

import game_interface.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Jugador extends Sprite {

    /**
     *  Identifier for each player
     */
    private int ID;


    private float dx;
    private float dy;

    //Salto
    private float speed;
    private float jumpStrength;
    private float weight;
    public int floorHeight = Constantes.HEIGHT - 61;
    public boolean jump;
    public int jumpCount;
    public boolean trueJump;
    private boolean left;
    private boolean right;

    /**
     * Number of player lifes
     */
    private int vidas;

    private String tipoJugador;//Popo o Nana

    Image image;

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

    public boolean isAttacking() {
        return isAttacking;
    }

    private boolean isAttacking;

    public Jugador(String tipoJugador){
        this.tipoJugador = tipoJugador;
        initJugador();
    }

    private void initJugador(){
        y = floorHeight;
        speed = 3;
        weight = 1;
        this.isAttacking = false;
        this.isDestroyed = false;
        this.right = false;
        this.left = false;

        this.jump = false;
        this.trueJump = false;
        this.jumpCount= 10;

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
            System.out.println("nanaaa");
            var ii = new ImageIcon("images/nanastand.png"); //imagen nana
            image = ii.getImage();
        }
    }

    @Override
    public void movement(){
        x += dx;
        //y -= dy;
        if (this.trueJump){
            salto();
        }
        golpe();


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

    public void checkOnGround(){

    }

    public void salto(){
        if(!this.jump) { //hay que revisar tambien
            this.jump = true;
            //System.out.println("salto");
        }


            if (this.jump){

                if (this.tipoJugador.equals("Popo")){
                    var ii = new ImageIcon("images/popojump.png"); //imagen nana
                    image = ii.getImage();
                    getImageDimensions();
                } else{
                    var ii = new ImageIcon("images/nanajump.png"); //imagen nana
                    image = ii.getImage();
                    getImageDimensions();
                }


                if (this.jumpCount >= -10) {

                    int negative = 1;
                    if (this.jumpCount < 0) {
                        negative = -1;
                    }
                    this.y -= Math.pow(this.jumpCount, 2) * 0.5 * negative;
                    this.jumpCount -= 1;

                    // devuelve el jugador al primer nivel
                    if (this.y == -78){
                        this.y = 550;
                        this.jump = false;
                        this.jumpCount = 10;
                        this.trueJump = false;
                    }
                    // tiene bug cuando se deja presionado SI HAY COLISION Y LAS SIGUITES COORDENADAS, SUBE DE PISO
                    switch (this.y) {
                        case 399, 253, 107 -> {
                            this.jump = false;
                            this.jumpCount = 10;
                            this.trueJump = false;
                            this.y += 5;
                        }
                    }

                } else{
                    this.jump = false;
                    this.jumpCount = 10;
                    this.trueJump = false;
                    this.y+=5;
                }
            }


    }

    public void golpe(){
//        var ii = new ImageIcon("images/popoattack.png"); //imagen nana
//        image = ii.getImage();
//        getImageDimensions();
        if(this.isAttacking && this.left){
            System.out.println("izq");
            if (this.getTipoJugador().equals("Popo")){
                var ii = new ImageIcon("images/popoattackleft.png"); //imagen nana
                image = ii.getImage();
                getImageDimensions();
            } else {
                System.out.println("holaaaa");
                var ii = new ImageIcon("images/nanaattackleft.png"); //imagen nana
                image = ii.getImage();
                getImageDimensions();
            }
        }
        if(this.isAttacking && this.right){

            if (this.tipoJugador.equals("Popo")){
                var ii = new ImageIcon("images/popoattackright.png"); //imagen nana
                image = ii.getImage();
                getImageDimensions();
            } else {
                var ii = new ImageIcon("images/nanaattackright.png"); //imagen nana
                image = ii.getImage();
                getImageDimensions();
            }
        }
//        if(!this.left && !this.right){
//            System.out.println("stand");
//            var ii = new ImageIcon("images/popostand.png"); //imagen nana
//            image = ii.getImage();
//            getImageDimensions();
//        }
        if(!this.isAttacking && !this.jump){

            if (this.tipoJugador.equals("Popo")){
                var ii = new ImageIcon("images/poporunright.png"); //imagen nana
                image = ii.getImage();
                getImageDimensions();
            } else {
                var ii = new ImageIcon("images/nanarunright.png"); //imagen nana
                image = ii.getImage();
                getImageDimensions();
            }
            //System.out.println("no hay ataque");

        }
    }


    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(tipoJugador.equals("Popo")){
            if(key == KeyEvent.VK_LEFT){
                dx = -3;
                this.right = false;
                this.left = true;
                this.isAttacking = false;
            }
            if(key == KeyEvent.VK_RIGHT){
                dx = 3;
                this.left = false;
                this.isAttacking = false;
                this.right = true;
            }
            if(key == KeyEvent.VK_UP ) { //hay que revisar tambien
                this.trueJump = true;
            }
            if(key == KeyEvent.VK_DOWN) { //hay que revisar tambien
                this.isAttacking = true;
                System.out.println("tecla");
            }


        }else{
            if(key == KeyEvent.VK_A){
            dx = -3;
                this.right = false;
                this.left = true;
                this.isAttacking = false;
            }
            if(key == KeyEvent.VK_D){
                dx = 3;
                this.left = false;
                this.isAttacking = false;
                this.right = true;
            }
            if(key == KeyEvent.VK_W){ //hay que revisar tambien.
                this.trueJump = true;
            }
            if(key == KeyEvent.VK_S) { //hay que revisar tambien
                this.isAttacking = true;
                System.out.println("tecla");
            }
        }
    }


    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(tipoJugador.equals("Popo")){
            if(key == KeyEvent.VK_LEFT){
                dx = 0;
                //this.left = true;
//                this.right = false;
//                this.left = false;
//                this.isAttacking = false;
            }
            if(key == KeyEvent.VK_RIGHT){
                dx = 0;
                //this.right = true;
//                this.right = false;
//                this.left = false;
//                this.isAttacking = false;
            }
//            if (key == KeyEvent.VK_UP) {
//                dy=0;
////            }
            if(key == KeyEvent.VK_DOWN){
                this.right = false;
                this.left = false;
                this.isAttacking = false;
            }


        }else{
            if(key == KeyEvent.VK_A){
                dx = 0;
            }
            if(key == KeyEvent.VK_D){
                dx = 0;
            }
            if(key == KeyEvent.VK_S){
                this.right = false;
                this.left = false;
                this.isAttacking = false;
            }
//            if (key == KeyEvent.VK_W){
//                dy = 0;
//            }
        }
    }

    boolean isJump(){
        return jump;
    }

    void setJump(boolean val){
        jump = val;
    }

    private void resetState(){
        x = Constantes.INIT_JUGADOR_X;
        y = Constantes.INIT_JUGADOR_Y;
        //y = floorHeight;
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
