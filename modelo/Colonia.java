package modelo;
import java.io.Serializable;

public class Colonia implements Serializable {
    private String claveColonia;
    private String nombreColonia;
    public Colonia(String claveColonia, String nombreColonia) {
        this.claveColonia = claveColonia;
        this.nombreColonia = nombreColonia;
    }
    public String getClaveColonia() { return claveColonia; }
    public void setClaveColonia(String claveColonia) { this.claveColonia = claveColonia; }
    public String getNombreColonia() { return nombreColonia; }
    public void setNombreColonia(String nombreColonia) { this.nombreColonia = nombreColonia; }
}