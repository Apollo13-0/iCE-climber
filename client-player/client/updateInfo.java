package client;

import game_interface.*;

import java.util.LinkedList;
import java.util.List;

public class updateInfo {

    /**
     * Identifier of the player
     */
    private int ID;

    /**
     * Coords in X of player 1
     */
    private int player1X;

    /**
     * Coords in Y of player 1
     */
    private int player1Y;

    /**
     * Coords in X of player 1
     */
    private int player2X;

    /**
     * Coords in Y of player 1
     */
    private int player2Y;

    /**
     * life of player 1
     */
    private int lifePlayer1;

    /**
     * life of player 2
     */
    private int lifePlayer2;

    /**
     *  score of player 1
     */
    private int scorePlayer1;

    /**
     *  score of player 2
     */
    private int scorePlayer2;

    /**
     *  all the sprites on the game
     */
    private List<Sprite2> spritesList;

    /**
     *  all the blocks on the game
     */
    private List<Sprite2> bloqueList;

    /**
     *  all the bonus on the game
     */
    private List<Sprite2> verdurasList;

    /**
     *  indicates if the game is in the bonus phase
     */
    private boolean bonusPhase;

    /**
     *  indicates the level of the game
     */
    private int level;

    /**
     *  constructor
     */
    public updateInfo(){

    }

    /**
     * constructor with all the information
     * @param ID int
     * @param player1X int
     * @param player1Y int
     * @param player2X int
     * @param player2Y int
     * @param lifePlayer1 int
     * @param lifePlayer2 int
     * @param scorePlayer1 int
     * @param scorePlayer2 int
     * @param spritesList Sprite
     * @param bloqueList Bloque
     * @param verdurasList Verdura
     * @param bonusPhase boolean
     * @param level int
     */
    public updateInfo(int ID, int player1X, int player1Y, int player2X, int player2Y, int lifePlayer1, int lifePlayer2, int scorePlayer1, int scorePlayer2, List<Sprite2> spritesList,List<Sprite2> bloqueList,List<Sprite2> verdurasList, boolean bonusPhase, int level) {
        this.ID = ID;
        this.player1X = player1X;
        this.player1Y = player1Y;
        this.player2X = player2X;
        this.player2Y = player2Y;
        this.lifePlayer1 = lifePlayer1;
        this.lifePlayer2 = lifePlayer2;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.spritesList = spritesList;
        this.bloqueList = bloqueList;
        this.verdurasList = verdurasList;
        this.bonusPhase = bonusPhase;
        this.level = level;
    }

    /**
     * sets all the information for one player
     * @param jugador Jugador
     * @param board Board
     */
    public void setSingle(Jugador jugador, Board board){
        this.setPlayer1X(jugador.getX());
        this.setPlayer1Y(jugador.getY());
        this.setLifePlayer1(board.getGameLives1());
        this.setScorePlayer1(board.getScoreJ1());
        this.setBonusPhase(board.getBonusPhase());
        this.setLevel(board.getLevel());
        this.setSpritesList(board.getSpritesList());
    }

    /**
     * sets all the information for 2 players
     * @param jugador1 Jugador
     * @param jugador2 Jugador
     * @param board Board
     */
    public void setCoop(Jugador jugador1, Jugador jugador2, Board board){
        this.setPlayer1X(jugador1.getX());
        this.setPlayer1Y(jugador1.getY());
        this.setLifePlayer1(board.getGameLives1());
        this.setScorePlayer1(board.getScoreJ1());

        this.setPlayer2X(jugador2.getX());
        this.setPlayer2Y(jugador2.getY());
        this.setLifePlayer2(board.getGameLives2());
        this.setScorePlayer2(board.getScoreJ2());

        this.setBonusPhase(board.getBonusPhase());
        this.setLevel(board.getLevel());
    }

    /**
     * Gets life of player 2.
     *
     * @return Value of life of player 2.
     */
    public int getLifePlayer2() {
        return lifePlayer2;
    }

    /**
     * Gets Coords in X of player 1.
     *
     * @return Value of Coords in X of player 1.
     */
    public int getPlayer2X() {
        return player2X;
    }

    /**
     * Sets new Coords in Y of player 1.
     *
     * @param player2Y New value of Coords in Y of player 1.
     */
    public void setPlayer2Y(int player2Y) {
        this.player2Y = player2Y;
    }

    /**
     * Sets new indicates if the game is in the bonus phase.
     *
     * @param bonusPhase New value of indicates if the game is in the bonus phase.
     */
    public void setBonusPhase(boolean bonusPhase) {
        this.bonusPhase = bonusPhase;
    }

    /**
     * Gets Coords in Y of player 1.
     *
     * @return Value of Coords in Y of player 1.
     */
    public int getPlayer2Y() {
        return player2Y;
    }

    /**
     * Sets new Coords in Y of player 1.
     *
     * @param player1Y New value of Coords in Y of player 1.
     */
    public void setPlayer1Y(int player1Y) {
        this.player1Y = player1Y;
    }

    /**
     * Gets Coords in Y of player 1.
     *
     * @return Value of Coords in Y of player 1.
     */
    public int getPlayer1Y() {
        return player1Y;
    }

    /**
     * Sets new score of player 1.
     *
     * @param scorePlayer1 New value of score of player 1.
     */
    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    /**
     * Gets indicates if the game is in the bonus phase.
     *
     * @return Value of indicates if the game is in the bonus phase.
     */
    public boolean isBonusPhase() {
        return bonusPhase;
    }

    /**
     * Sets new life of player 2.
     *
     * @param lifePlayer2 New value of life of player 2.
     */
    public void setLifePlayer2(int lifePlayer2) {
        this.lifePlayer2 = lifePlayer2;
    }

    /**
     * Sets new Coords in X of player 1.
     *
     * @param player1X New value of Coords in X of player 1.
     */
    public void setPlayer1X(int player1X) {
        this.player1X = player1X;
    }

    /**
     * Gets score of player 1.
     *
     * @return Value of score of player 1.
     */
    public int getScorePlayer1() {
        return scorePlayer1;
    }

    /**
     * Sets new score of player 2.
     *
     * @param scorePlayer2 New value of score of player 2.
     */
    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    /**
     * Gets Identifier of the player.
     *
     * @return Value of Identifier of the player.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets new life of player 1.
     *
     * @param lifePlayer1 New value of life of player 1.
     */
    public void setLifePlayer1(int lifePlayer1) {
        this.lifePlayer1 = lifePlayer1;
    }

    /**
     * Gets score of player 2.
     *
     * @return Value of score of player 2.
     */
    public int getScorePlayer2() {
        return scorePlayer2;
    }

    /**
     * Sets new Coords in X of player 1.
     *
     * @param player2X New value of Coords in X of player 1.
     */
    public void setPlayer2X(int player2X) {
        this.player2X = player2X;
    }

    /**
     * Gets Coords in X of player 1.
     *
     * @return Value of Coords in X of player 1.
     */
    public int getPlayer1X() {
        return player1X;
    }

    /**
     * Sets new Identifier of the player.
     *
     * @param ID New value of Identifier of the player.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets life of player 1.
     *
     * @return Value of life of player 1.
     */
    public int getLifePlayer1() {
        return lifePlayer1;
    }

    /**
     *  Gets level
     * @return int
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level
     * @param level int
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * gets sprites list
     * @return
     */
    public List<Sprite2> getSpritesList() {
        return spritesList;
    }

    /**
     * sets spriteList
     * @param spritesList
     */
    public void setSpritesList(List<Sprite2> spritesList) {
        this.spritesList = spritesList;
    }

    /**
     * gets blockList
     * @return
     */
    public List<Sprite2> getBloqueList() {
        return bloqueList;
    }

    /**
     * sets blocklist
     * @param bloqueList
     */
    public void setBloqueList(List<Sprite2> bloqueList) {
        this.bloqueList = bloqueList;
    }

    /**
     * Sets new all the bonus on the game.
     *
     * @param verdurasList New value of all the bonus on the game.
     */
    public void setVerdurasList(List<Sprite2> verdurasList) {
        this.verdurasList = verdurasList;
    }

    /**
     * Gets all the bonus on the game.
     *
     * @return Value of all the bonus on the game.
     */
    public List<Sprite2> getVerdurasList() {
        return verdurasList;
    }
}
