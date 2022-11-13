package sprites;

public class foca extends enemy {

    /**
     * Indicates the direction of the foca
     */
    private String dir;

    /**
     *  builts a foca object in a desired floor
     */
    public foca(int floor, String dir){
        this.setName("foca");
        this.setSpritePath("resources\foca.png");
        this.setDir(dir);
    }

    /**
     *  Sets the floor and Y coords of the foca
     * @param floor int range 1 - 15
     */
    @Override
    public void setFloor(int floor) {
        this.setFloor(floor);

        // revisar coordenadas
        switch (floor){
            case 1 -> this.setYCoord(700);
            case 2 -> this.setYCoord(525);
            case 3 -> this.setYCoord(350);
            case 4 -> this.setYCoord(175);
            case 5 -> this.setYCoord(700);
            case 6 -> this.setYCoord(525);
            case 7 -> this.setYCoord(350);
            case 8 -> this.setYCoord(175);
            case 9 -> this.setYCoord(700);
            case 10 -> this.setYCoord(525);
            case 11 -> this.setYCoord(350);
            case 12 -> this.setYCoord(175);
            case 13 -> this.setYCoord(700);
            case 14 -> this.setYCoord(525);
            case 15 -> this.setYCoord(350);
        }
        
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
        if (dir.equals("DI")){
            this.setXCoord(300); // ver cual es el ancho de la ventana
        } else if (dir.equals("ID")){
            this.setXCoord(0);
        } else{
            System.out.println("Direción incorrecta");
        }
    }
}

