package modelo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDatos {
    private List<Usuario> usuarios;
    private List<Colonia> colonias;
    private Tarifas tarifas;
    public GestorDatos() {
        usuarios = new ArrayList<>();
        colonias = new ArrayList<>();
        tarifas = new Tarifas();
    }
    public void guardarDatos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("datos_agua.dat"))) {
            out.writeObject(usuarios);
            out.writeObject(colonias);
            out.writeObject(tarifas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        File archivo = new File("datos_agua.dat");
        if (archivo.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
                usuarios = (List<Usuario>) in.readObject();
                colonias = (List<Colonia>) in.readObject();
                tarifas = (Tarifas) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Colonia> getColonias() { return colonias; }
    public Tarifas getTarifas() { return tarifas; }
}