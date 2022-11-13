package sprites;

/**
 *  This class creates players for the game
 */
public class player {

    /**
     *  Identifier for each player
     */
    private int ID;

    /**
     *  Name of the player (Popo or Nana)
     */
    private String name;

    /**
     *  Coord in x
     */
    private int xCoord;

    /**
     *  Coord in y
     */
    private int yCoord;

    /**
     *  Number of player lifes
     */
    private int lifes;

    /**
     * Sets new Number of player lifes.
     *
     * @param lifes New value of Number of player lifes.
     */
    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    /**
     * Gets Name of the player Popo or Nana.
     *
     * @return Value of Name of the player Popo or Nana.
     */
    public String getName() {
        return name;
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
     * Sets new Coord in x.
     *
     * @param xCoord New value of Coord in x.
     */
    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Gets Number of player lifes.
     *
     * @return Value of Number of player lifes.
     */
    public int getLifes() {
        return lifes;
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
     * Sets new Identifier for each player.
     *
     * @param ID New value of Identifier for each player.
     */
    public void setID(int ID) {
        this.ID = ID;
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
     * Gets Identifier for each player.
     *
     * @return Value of Identifier for each player.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets new Name of the player Popo or Nana.
     *
     * @param name New value of Name of the player Popo or Nana.
     */
    public void setName(String name) {
        this.name = name;
    }
}
