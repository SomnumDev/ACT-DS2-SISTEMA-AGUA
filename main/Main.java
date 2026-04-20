package main;
import vista.Vista;
import modelo.GestorDatos;
import controlador.Controlador;
public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        GestorDatos modelo = new GestorDatos();
        Controlador controlador = new Controlador(vista, modelo);
        controlador.iniciar();
    }
}