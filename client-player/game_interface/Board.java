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
import java.util.IllegalFormatCodePointException;
import java.util.Objects;

public class Board extends JPanel{
    private Timer timer;
    private String message = "Game Over";
    public String tipoJuego;
    private Jugador jugador1; //Popo
    private Jugador jugador2; //Nana
    private int scoreJ1 = 0; //Puntos Popo
    private int scoreJ2 = 0; //Puntos Nana
    private int level;
    private int gameLives1;
    private int gameLives2;
    private boolean inGame = true;
    private updateInfo updateInfo;
    private clientLogic client;
    private boolean bonusPhase;
    private int pisoA;
    private int pisoB;
    private int pisoC;
    private int pisoD;
    private String bonusLabel;

    //enemies
    private Foca foca;
    private Ave ave; //Hace falta meterlo en una lista.
    private Ave ave2;
    private Ave[] aves;

    //bloques
    private Bloque[] bloques5p;
    private Bloque[] bloques4p;
    private Bloque[] bloques3p;
    private Bloque[] bloques2p;
    private Bloque[] bloques1p;

    public Board(String tipoJuego){
        initBoard(tipoJuego);
    }

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
        foca = new Foca(4, "ID");
        ave = new Ave(3);
        ave2 = new Ave(2);

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


        g2d.drawImage(foca.getImage(),foca.getX(),foca.getY(),foca.getImageWidth(),foca.getImageHeight(),this);
        g2d.drawImage(ave.getImage(),ave.getX(),ave.getY(),ave.getImageWidth(),ave.getImageHeight(),this);
        g2d.drawImage(ave2.getImage(),ave2.getX(),ave2.getY(),ave2.getImageWidth(),ave2.getImageHeight(),this);
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
            //jugador1.keyPressed(e);
            //jugador2.keyPressed(e);
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

        if(this.tipoJuego.equals("Single")){
            jugador1.movement();

            if (jugador1.getY() == -77){
                this.level+=1;
                setFloorLabel(1);
            }

            this.updateInfo.setSingle(jugador1, this);
        }

        if(this.tipoJuego.equals("Coop")){
            jugador1.movement();
            jugador2.movement();

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

        repaint();
    }

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

    private void stopGame(){
        inGame = false;
        timer.stop();
    }

    private void checkCollision(){

    }

    private void checkLevel(int index){

    }


    public boolean getBonusPhase() {
        return bonusPhase;
    }

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
