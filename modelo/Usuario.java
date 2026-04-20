package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int numeroUsuario;
    private String nombre;
    private String direccion;
    private String claveColonia;
    private int tipoUsuario;
    private double consumo;

    public Usuario(int numeroUsuario, String nombre, String direccion, String claveColonia, int tipoUsuario, double consumo) {
        this.numeroUsuario = numeroUsuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.claveColonia = claveColonia;
        this.tipoUsuario = tipoUsuario;
        this.consumo = consumo;
    }

    public int getNumeroUsuario() { return numeroUsuario; }
    public void setNumeroUsuario(int numeroUsuario) { this.numeroUsuario = numeroUsuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getClaveColonia() { return claveColonia; }
    public void setClaveColonia(String claveColonia) { this.claveColonia = claveColonia; }
    public int getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(int tipoUsuario) { this.tipoUsuario = tipoUsuario; }
    public double getConsumo() { return consumo; }
    public void setConsumo(double consumo) { this.consumo = consumo; }
}