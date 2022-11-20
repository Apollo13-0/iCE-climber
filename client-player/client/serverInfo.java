package client;

public class serverInfo {

    /**
     * indica el tipo de mensaje
     */
    private String tipo;

    /**
     * indica el id
     */
    private int feedback;

    /**
     * Nombre del enemigo nuevo
     */
    private String nombre;

    /**
     * Indica la dirección de la foca
     */
    private String direccion;

    /**
     * indica el piso donde aparece el enemigo
     */
    private int piso;

    /**
     * Constructor
     * @param tipo string
     * @param feedback int
     * @param nombre string
     * @param direccion string
     * @param piso int
     */
    public serverInfo(String tipo, int feedback, String nombre, String direccion, int piso) {
        this.tipo = tipo;
        this.feedback = feedback;
        this.nombre = nombre;
        this.direccion = direccion;
        this.piso = piso;
    }

    /**
     * constructor
     */
    public serverInfo() {
    }

    /**
     * gets type
     * @return String
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * set type
     * @param tipo string
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * gets feedback
     * @return int
     */
    public int getFeedback() {
        return feedback;
    }

    /**
     * set feedback
     * @param feedback int
     */
    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    /**
     * gets name
     * @return string
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * sets name
     * @param nombre string
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * gets direction
     * @return string
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * sets direction
     * @param direccion string
     */

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * gets floor
     * @return int
     */

    public int getPiso() {
        return piso;
    }

    /**
     * sets floor
     * @param piso int
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }
}
