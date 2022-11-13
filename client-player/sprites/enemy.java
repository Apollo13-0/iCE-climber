package sprites;

/**
 *  Clase abstracta para los enemigos (focas, aves, hielos)
 */
public abstract class enemy {

    /**
     *  Name of the enemy
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
     * Indicates the current floor of the foca
     */
    private int floor;

    /**
     *  Directory of the sprite image
     */
    private String spritePath;

    /**
     *  Creator of the abstract class
     */
    public enemy(){}


    /**
     * Sets new Name of the enemy.
     *
     * @param name New value of Name of the enemy.
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets Directory of the sprite image.
     *
     * @return Value of Directory of the sprite image.
     */
    public String getSpritePath() {
        return spritePath;
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
     * Sets new Directory of the sprite image.
     *
     * @param spritePath New value of Directory of the sprite image.
     */
    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
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
     * Gets Name of the enemy.
     *
     * @return Value of Name of the enemy.
     */
    public String getName() {
        return name;
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
     * Gets the current floor
     *
     * @return Value of current floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Sets new floor
     *
     * @param floor int
     */
    public abstract void setFloor(int floor);
}
