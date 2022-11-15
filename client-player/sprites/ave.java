package sprites;
import java.util.Random;

public class ave extends enemy {

    /**
     * Indicates the direction of the foca
     */
    private String dir;

    public ave(){
        this.setName("ave");
        this.setSpritePath("resources\foca.png");
        this.setXCoord(0);

        Random rand = new Random();
        this.setFloor(rand.nextInt(3) +1 );
    }

    /**
     * takes a random number in range 1-4 and sets new coords
     * @param floor int
     */
    @Override
    public void setFloor(int floor) {

        switch (floor) {
            case 1 -> this.setYCoord(700);
            case 2 -> this.setYCoord(525);
            case 3 -> this.setYCoord(350);
            case 4 -> this.setYCoord(175);
        }
    }
}
