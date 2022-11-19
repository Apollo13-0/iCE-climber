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

    public serverInfo(String tipo, int feedback, String nombre, String direccion, int piso) {
        this.tipo = tipo;
        this.feedback = feedback;
        this.nombre = nombre;
        this.direccion = direccion;
        this.piso = piso;
    }

    public serverInfo() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
