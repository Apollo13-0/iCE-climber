package game_interface;

import game_interface.Jugador;
import game_interface.Foca;

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

public class Board extends JPanel{
    private Timer timer;
    private String message = "Game Over";
    private Jugador jugador1; //Popo
    private Jugador jugador2; //Nana
    private int scoreJ1 = 0; //Puntos Popo
    private int scoreJ2 = 0; //Puntos Nana
    private int level = 1;
    private int gameLives1;
    private int gameLives2;
    private boolean inGame = true;

    //enemies
    private Foca foca;
    private Ave ave; //Hace falta meterlo en una lista.
    private Ave ave2;
    private Ave[] aves;

    public Board(){
        initBoard();
    }

    public static void updateGameDetails(){


    }

    /**
     * Inicializador de la clase game_interface.Board
     */
    private void initBoard(){

        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Constantes.WIDTH, Constantes.HEIGHT));
        setBackground(Color.BLACK);

        gameInit();
    }

    /**
     * Inicializador con los datos de cada objeto del juego.
     */

    private void gameInit(){
        jugador1 = new Jugador("Popo");
        jugador2 = new Jugador("Nana");
        gameLives1 = 3;
        gameLives2 = 3;
        foca = new Foca(1, "ID");
        ave = new Ave(3);
        ave2 = new Ave(2);

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

        g2d.drawImage(jugador1.getImage(),jugador1.getX(),jugador1.getY(),jugador1.getImageWidth(),jugador1.getImageHeight(),this);
        g2d.drawImage(jugador2.getImage(),jugador2.getX(),jugador2.getY(),jugador2.getImageWidth(),jugador2.getImageHeight(),this);
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
        g2d.drawString("Score: " + String.valueOf(scoreJ1), (Constantes.WIDTH - fontMetrics.stringWidth(String.valueOf(scoreJ1)))/2, 20);
        g2d.drawString("Score: " + String.valueOf(scoreJ2), (Constantes.WIDTH - fontMetrics.stringWidth(String.valueOf(scoreJ2)))/2, 20);
        g2d.drawString("Lives: " + String.valueOf(gameLives1), (Constantes.WIDTH - fontMetrics.stringWidth(String.valueOf(gameLives1))/3), 20);
        g2d.drawString("Lives: " + String.valueOf(gameLives2), (Constantes.WIDTH - fontMetrics.stringWidth(String.valueOf(gameLives2))/3), 20);
        g2d.drawString("Level: " + String.valueOf(level), (Constantes.WIDTH - fontMetrics.stringWidth(String.valueOf(level)))/5, 20);
    }

    /**
     * Clase para chequear las teclas (cuando son presionadas o dejan de estarlo).
     */
    private class TAdapter extends KeyAdapter{
        /**
         * Metodo para chequear si un tecla dejar de estar presionada.
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e){
            jugador1.keyReleased(e);
            jugador2.keyReleased(e);
        }

        /**
         * Metodo para chequear si un tecla está siendo presionada.
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e){
            jugador1.keyPressed(e);
            jugador2.keyPressed(e);
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
        jugador1.movement();
        jugador2.movement();
        foca.movement();
        ave.movement();
        ave2.movement();
        //System.out.println("HOLA IGNACIO"); //Agregue aquí la actualizacion de la informacion del servidor.

        repaint();
    }

    private void stopGame(){
        inGame = false;
        timer.stop();
    }

    private void checkCollision(){

    }

    private void checkLevel(int index){

    }

}
