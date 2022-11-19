package game_interface;

import com.google.gson.Gson;
import game_interface.Jugador;
import game_interface.Foca;


import client.updateInfo;
import client.clientLogic;



import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class Board extends JPanel{
    private Timer timer;

    /**
     * indicates the state of the game
     */
    private String message = "Game Over";

    /**
     * Indicates the type of game
     */
    public String tipoJuego;

    /**
     * Sprite of popo
     */
    private Jugador jugador1;

    /**
     * Sprite of nana
     */
    private Jugador jugador2;

    /**
     * Score points of popo
     */
    private int scoreJ1 = 0;

    /**
     * Score points of nana
     */
    private int scoreJ2 = 0;

    /**
     * Indicates current level
     */
    private int level;

    /**
     * Stores the number of popo's lives
     */
    private int gameLives1;

    /**
     * Stores the number of nana's lives
     */
    private int gameLives2;

    /**
     *
     */
    private boolean inGame = true;

    /**
     * Class that handles the info for the server
     */
    private updateInfo updateInfo;

    /**
     *  Client
     */
    private clientLogic client;

    /**
     * Boolean indicates if is in bonus phase
     */
    private boolean bonusPhase;

    /**
     * inf for floor label
     */
    private int pisoA;

    /**
     * inf for floor label
     */
    private int pisoB;

    /**
     * inf for floor label
     */
    private int pisoC;

    /**
     * inf for floor label
     */
    private int pisoD;

    /**
     * String for bonus label
     */
    private String bonusLabel;

    //enemigos
    private Foca foca;
    private Ave ave; //Hace falta meterlo en una lista.
    private Ave ave2;
    private Ave[] aves;
    private Hielo hielo;
    private Pterodactilo pterodactilo;


    //Lista de enemigos
    final List<Sprite> enemigos = new ArrayList<>();

    /**
     * Blocks obj for each floor
     */
    private Bloque[] bloques5p;
    private Bloque[] bloques4p;
    private Bloque[] bloques3p;
    private Bloque[] bloques2p;
    private Bloque[] bloques1p;

    /**
     * Constructor of Board class
     * @param tipoJuego String
     */
    public Board(String tipoJuego){
        initBoard(tipoJuego);
    }

    /**
     * Handles the creation of enemigos, reads the server
     */
    public static void updateGameDetails(){

    }

    /**
     * Inicializador de la clase game_interface.Board
     */
    private void initBoard(String tipoJuego){
        this.pisoA = 1;
        this.pisoB = 2;
        this.pisoC = 3;
        this.pisoD = 4;
        this.bonusLabel = "";
        this.tipoJuego = tipoJuego;
        this.level = 1;
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Constantes.WIDTH, Constantes.HEIGHT));
        setBackground(Color.BLACK);
        this.updateInfo = new updateInfo();
        //this.client = new clientLogic(6666, "127.0.0.1");
        gameInit();
    }

    /**
     * Inicializador con los datos de cada objeto del juego.
     */

    private void gameInit(){

        bloques4p = new Bloque[Constantes.NUMBER_OF_BLOCKS];
        bloques3p = new Bloque[Constantes.NUMBER_OF_BLOCKS2];
        bloques2p = new Bloque[Constantes.NUMBER_OF_BLOCKS3];
        bloques1p = new Bloque[Constantes.NUMBER_OF_BLOCKS4];
        bloques5p = new Bloque[Constantes.NUMBER_OF_BLOCKS5];

        if(this.tipoJuego.equals("Single")){
            jugador1 = new Jugador("Popo");
            gameLives1 = 3;
        }
        if(this.tipoJuego.equals("Coop")){
            jugador1 = new Jugador("Popo");
            jugador2 = new Jugador("Nana");
            gameLives1 = 3;
            gameLives2 = 3;
        }

        ///---------------------------PRUEBAS---------------------------------
       //final List<Sprite> enemigos = new ArrayList<>();
        foca = new Foca(4, "ID");
        ave = new Ave(3);
        ave2 = new Ave(2);
        enemigos.add(foca);
        enemigos.add(ave);
        enemigos.add(ave2);
        ///-------------------------------------------------------------------


        //Piso 4
        int k = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 39; j++){
                bloques4p[k] = new Bloque(j * 20 + 10, i * 20 + 30);
                k++;
            }
        }

        //Piso 3
        int l = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 39; j++){
                bloques3p[l] = new Bloque(j * 20 + 10, i * 20 + 175);
                l++;
            }
        }

        //Piso 2
        int m = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 39; j++){
                bloques2p[m] = new Bloque(j * 20 + 10, i * 20 + 320);
                m++;
            }
        }

        //Piso 1
        int n = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 39; j++){
                bloques1p[n] = new Bloque(j * 20 + 10, i * 20 + 465);
                n++;
            }
        }

        //Piso 5
        int o = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 39; j++){
                bloques5p[o] = new Bloque(j * 20 + 10, i * 20 + 610);
                o++;
            }
        }

        timer = new Timer(20, new Gamecycle());
        timer.start();
    }

    /**
     * Metodo encargar de dibujar los componentes en pantalla.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        var g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

        if(inGame){
            drawObjects(g2d);
            showGameMetrics(g2d);
        }else{
            gameFinished(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Metodo para dibujar los objetos como los enemigos, jugadores, etc.
     * @param g2d
     */
    private void drawObjects(Graphics2D g2d){

        for(int i = 0; i < Constantes.NUMBER_OF_BLOCKS; i++){
            if(!bloques4p[i].isDestroyed()){
                g2d.drawImage(bloques4p[i].getImage(), bloques4p[i].getX(), bloques4p[i].getY(), bloques4p[i].getImageWidth(), bloques4p[i].getImageHeight(),
                        this);
            }
        }

        for(int i = 0; i < Constantes.NUMBER_OF_BLOCKS2; i++){
            if(!bloques3p[i].isDestroyed()){
                g2d.drawImage(bloques3p[i].getImage(), bloques3p[i].getX(), bloques3p[i].getY(), bloques3p[i].getImageWidth(), bloques3p[i].getImageHeight(),
                        this);
            }
        }

        for(int i = 0; i < Constantes.NUMBER_OF_BLOCKS3; i++){
            if(!bloques2p[i].isDestroyed()){
                g2d.drawImage(bloques2p[i].getImage(), bloques2p[i].getX(), bloques2p[i].getY(), bloques2p[i].getImageWidth(), bloques2p[i].getImageHeight(),
                        this);
            }
        }

        for(int i = 0; i < Constantes.NUMBER_OF_BLOCKS4; i++){
            if(!bloques1p[i].isDestroyed()){
                g2d.drawImage(bloques1p[i].getImage(), bloques1p[i].getX(), bloques1p[i].getY(), bloques1p[i].getImageWidth(), bloques1p[i].getImageHeight(),
                        this);
            }
        }

        for(int i = 0; i < Constantes.NUMBER_OF_BLOCKS5; i++){
            if(!bloques5p[i].isDestroyed()){
                g2d.drawImage(bloques5p[i].getImage(), bloques5p[i].getX(), bloques5p[i].getY(), bloques5p[i].getImageWidth(), bloques5p[i].getImageHeight(),
                        this);
            }
        }

        if(this.tipoJuego.equals("Single")){
            g2d.drawImage(jugador1.getImage(),jugador1.getX(),jugador1.getY(),jugador1.getImageWidth(),jugador1.getImageHeight(),this);
        }
        if(this.tipoJuego.equals("Coop")){
            g2d.drawImage(jugador1.getImage(),jugador1.getX(),jugador1.getY(),jugador1.getImageWidth(),jugador1.getImageHeight(),this);
            g2d.drawImage(jugador2.getImage(),jugador2.getX(),jugador2.getY(),jugador2.getImageWidth(),jugador2.getImageHeight(),this);
        }

        for (int i = 0; i < this.enemigos.size(); i++){
            if(!this.enemigos.get(i).isDestroyed()) {
                g2d.drawImage(this.enemigos.get(i).getImage(), this.enemigos.get(i).getX(), this.enemigos.get(i).getY(), this.enemigos.get(i).getImageWidth(), this.enemigos.get(i).getImageHeight(), this);

            }
        }

//        g2d.drawImage(foca.getImage(),foca.getX(),foca.getY(),foca.getImageWidth(),foca.getImageHeight(),this);
//        g2d.drawImage(ave.getImage(),ave.getX(),ave.getY(),ave.getImageWidth(),ave.getImageHeight(),this);
//        g2d.drawImage(ave2.getImage(),ave2.getX(),ave2.getY(),ave2.getImageWidth(),ave2.getImageHeight(),this);
    }

    /**
     * metodo para cuando se acaba el juego.
     * @param g2d
     */
    private void gameFinished(Graphics2D g2d){
        var font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message, (Constantes.WIDTH - fontMetrics.stringWidth(message))/2, Constantes.WIDTH / 2);
        g2d.drawString(("Puntaje: " + String.valueOf(scoreJ1)), (Constantes.WIDTH - fontMetrics.stringWidth(message))/2, 400);
        g2d.drawString(("Puntaje: " + String.valueOf(scoreJ2)), (Constantes.WIDTH - fontMetrics.stringWidth(message))/2, 500);
    }

    /**
     * Metodo para mostrar vidas, puntaje y el nivel.
     * @param g2d
     */
    private void showGameMetrics(Graphics2D g2d){
        var font = new Font("Verdana", Font.BOLD,18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString("Score 1: " + String.valueOf(scoreJ1), 40, 20);
        g2d.drawString("Score 2: " + String.valueOf(scoreJ2), 165, 20);
        g2d.drawString("Lives 1: " + String.valueOf(gameLives1), 290, 20);
        g2d.drawString("Lives 2: " + String.valueOf(gameLives2), 415, 20);
        g2d.drawString("Level: " + String.valueOf(level), 540, 20);

        g2d.drawString(String.valueOf(pisoD), 40, 100);
        g2d.drawString(String.valueOf(pisoC), 40, 235);
        g2d.drawString(String.valueOf(pisoB), 40, 380);
        g2d.drawString(String.valueOf(pisoA), 40, 525);


        g2d.drawString(String.valueOf(pisoD), 750, 100);
        g2d.drawString(String.valueOf(pisoC), 750, 235);
        g2d.drawString(String.valueOf(pisoB), 750, 380);
        g2d.drawString(String.valueOf(pisoA), 750, 525);

        g2d.drawString(bonusLabel, 350, 100);


    }

    /**
     * Clase para chequear las teclas (cuando son presionadas o dejan de estarlo).
     */
    private class TAdapter extends KeyAdapter {
        /**
         * Metodo para chequear si un tecla dejar de estar presionada.
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e){
            if(jugador2 == null){
                jugador1.keyReleased(e);
            }else{
                jugador1.keyReleased(e);
                jugador2.keyReleased(e);
            }
            //jugador1.keyReleased(e);
            //jugador2.keyReleased(e);
        }

        /**
         * Metodo para chequear si un tecla está siendo presionada.
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e){

            if(jugador2 == null){
                jugador1.keyPressed(e);
            }else{
                jugador1.keyPressed(e);
                jugador2.keyPressed(e);
            }
        }
    }

    /**
     * Clase para chequear cada evento realizado
     */
    private class Gamecycle implements ActionListener{
        /**
         * Este metodo es para chequear si una accion fue realizada.
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e){
            doGameCycle();
        }
    }

    /**
     * Metodos
     */
    private void doGameCycle(){

        // Partida de un jugador
        if(this.tipoJuego.equals("Single")){
            jugador1.movement();
            checkCollision(jugador1);

            // LLega el nivel superior
            if (jugador1.getY() == -77){
                this.level+=1;
                setFloorLabel(1);
            }

            this.updateInfo.setSingle(jugador1, this);
        }

        // Partida cooperativa
        if(this.tipoJuego.equals("Coop")){
            jugador1.movement();
            jugador2.movement();
            checkCollision(jugador1);
            checkCollision(jugador2);

            // Solo pasa el jugador 1 al siguiente nivel
            if (jugador1.getY() == -77){
                this.level+=1;
                this.gameLives2-=1;
                jugador2.setY(550);
                setFloorLabel(1);
            }

            // Solo pasa el jugador 2 al siguiente nivel
            if (jugador2.getY() == -77){
                this.level+=1;
                this.gameLives1-=1;
                jugador1.setY(550);
                setFloorLabel(2);
            }

            // ambos pasan al siguiente nivel
            if (jugador1.getY() == -77 && jugador2.getY() == -77){
                this.level+=1;
                setFloorLabel(3);
            }

            this.updateInfo.setCoop(jugador1, jugador2, this);
        }

        foca.movement();
        ave.movement();
        ave2.movement();

        //this.client.writeSocket(new Gson().toJson(this.updateInfo));
        //checkCollision();
        repaint();
    }

    /**
     * Sets the number of the floor
     * @param n int type of game
     */
    private void setFloorLabel(int n){
        if (this.level == 3){
            this.bonusLabel = "Fase bonus";
            if (n == 1){
                gameLives1+=1;
            } else if(n ==2){
                gameLives2+=1;
            } else if (n == 3){
                gameLives1+=1;
                gameLives2+=1;
            }

            // aqui debe de recorrer la lista de enemigos y cambiar la velocidad a cada uno

        } else {
            this.pisoA+=4;
            this.pisoB+=4;
            this.pisoC+=4;
            this.pisoD+=4;
        }
    }

    /**
     * Stops the game
     */
    private void stopGame(){
        inGame = false;
        timer.stop();
    }

    private void checkCollision(Jugador jugador){
        for(int k = 0; k < Constantes.NUMBER_OF_BLOCKS2; k++){ //Colision bloques, el cual hay que revisar metodo salto del jugador
            if((jugador.getRectangle()).intersects((bloques2p[k].getRectangle()))){
                bloques2p[k].setDestroyed(true);

            }
        }
        for(int k = 0; k < enemigos.size(); k++){
            if(jugador.getRectangle().intersects(enemigos.get(k).getRectangle()) && jugador.isAttacking()){
                System.out.println("colision");
                if(enemigos.get(k).getName() == "ave"){
                    if(jugador.getName().equals("Popo")){
                        this.scoreJ1 += Constantes.AVE_POINTS;
                    }else{
                        this.scoreJ2 += Constantes.AVE_POINTS;
                    }
                    //this.scoreJ1 += Constantes.AVE_POINTS;
                    System.out.println("golpe");
                }
                if(enemigos.get(k).getName() == "foca"){
                    if(jugador.getName().equals("Popo")){
                        this.scoreJ1 += Constantes.FOCA_POINTS;
                    }else{
                        this.scoreJ2 += Constantes.FOCA_POINTS;
                    }
                    //this.scoreJ1 += Constantes.FOCA_POINTS;
                    System.out.println("golpefoca");
                }

                enemigos.get(k).setDestroyed(true);
                enemigos.remove(k);

            }
            if(jugador.getRectangle().intersects(enemigos.get(k).getRectangle())){
                System.out.println("canazo");
                if(jugador == jugador1){
                    this.gameLives1 -= 1;
                }else{
                    this.gameLives2 -= 1;
                }

                jugador.y = 550;
                jugador.jump = false;
                jugador.jumpCount = 10;
                jugador.trueJump = false;
            }
        }
    }

    private void checkLevel(int index){

    }

    /**
     * Checks the bonus phase
     * @return boolean
     */
    public boolean getBonusPhase() {
        return bonusPhase;
    }

    /**
     * sets bonus phase
     * @param bonusPhase boolean
     */
    public void setBonusPhase(boolean bonusPhase) {
        this.bonusPhase = bonusPhase;
    }

    /**
     * Gets level.
     *
     * @return Value of level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets new scoreJ2.
     *
     * @param scoreJ2 New value of scoreJ2.
     */
    public void setScoreJ2(int scoreJ2) {
        this.scoreJ2 = scoreJ2;
    }

    /**
     * Sets new level.
     *
     * @param level New value of level.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets scoreJ2.
     *
     * @return Value of scoreJ2.
     */
    public int getScoreJ2() {
        return scoreJ2;
    }

    /**
     * Sets new scoreJ1.
     *
     * @param scoreJ1 New value of scoreJ1.
     */
    public void setScoreJ1(int scoreJ1) {
        this.scoreJ1 = scoreJ1;
    }

    /**
     * Gets scoreJ1.
     *
     * @return Value of scoreJ1.
     */
    public int getScoreJ1() {
        return scoreJ1;
    }
}
