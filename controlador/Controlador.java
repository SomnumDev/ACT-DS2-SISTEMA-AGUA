package controlador;

import modelo.*;
import vista.Vista;
import javax.swing.JOptionPane;
import java.awt.CardLayout;

public class Controlador {
    private Vista vista;
    private GestorDatos modelo;

    public Controlador(Vista vista, GestorDatos modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.modelo.cargarDatos();
        inicializarVista();

        this.vista.btnIrUsuarios.addActionListener(e -> mostrarPanel("CardUsuarios"));
        this.vista.btnIrColonias.addActionListener(e -> mostrarPanel("CardColonias"));
        this.vista.btnIrTarifas.addActionListener(e -> mostrarPanel("CardTarifas"));

        this.vista.btnGuardarUsuario.addActionListener(e -> guardarUsuario());
        this.vista.btnBuscarUsuario.addActionListener(e -> buscarUsuario());
        this.vista.btnActualizarUsuario.addActionListener(e -> actualizarUsuario());
        this.vista.btnEliminarUsuario.addActionListener(e -> eliminarUsuario());

        this.vista.btnGuardarCol.addActionListener(e -> guardarColonia());
        this.vista.btnBuscarCol.addActionListener(e -> buscarColonia());
        this.vista.btnActualizarCol.addActionListener(e -> actualizarColonia());
        this.vista.btnEliminarCol.addActionListener(e -> eliminarColonia());

        this.vista.btnActualizarTarif.addActionListener(e -> actualizarTarifa());

        if(this.vista.cbTipoTarif != null) {
            this.vista.cbTipoTarif.addActionListener(e -> cargarMontoTarifaSeleccionada());
        }
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    private void mostrarPanel(String nombrePanel) {
        if (vista.panelCards != null && vista.panelCards.getLayout() instanceof CardLayout) {
            CardLayout cl = (CardLayout) vista.panelCards.getLayout();
            cl.show(vista.panelCards, nombrePanel);
        }
    }

    private void inicializarVista() {
        if(vista.cbTipoUsuario != null) {
            vista.cbTipoUsuario.removeAllItems();
            for(int i = 1; i <= 8; i++) vista.cbTipoUsuario.addItem(i);
        }
        if(vista.cbTipoTarif != null) {
            vista.cbTipoTarif.removeAllItems();
            for(int i = 1; i <= 8; i++) vista.cbTipoTarif.addItem(i);
            cargarMontoTarifaSeleccionada();
        }
        actualizarTablaUsuarios();
        actualizarTablaColonias();
        actualizarTablaTarifas();
    }

    private void cargarMontoTarifaSeleccionada() {
        if (vista.cbTipoTarif.getSelectedItem() != null) {
            int tipo = (int) vista.cbTipoTarif.getSelectedItem();
            double montoActual = modelo.getTarifas().getTarifa(tipo);
            vista.txtMontoTarif.setText(String.valueOf(montoActual));
        }
    }

    private void guardarUsuario() {
        try {
            int numero = Integer.parseInt(vista.txtNumUsuario.getText());
            for (Usuario u : modelo.getUsuarios()) {
                if (u.getNumeroUsuario() == numero) {
                    JOptionPane.showMessageDialog(vista, "El numero ya existe");
                    return;
                }
            }
            String nombre = vista.txtNomUsuario.getText();
            String direccion = vista.txtDirUsuario.getText();
            String claveColonia = vista.txtClaveCol.getText();
            int tipo = (int) vista.cbTipoUsuario.getSelectedItem();
            double consumo = Double.parseDouble(vista.txtConsumoUsuario.getText());

            if (!validarColoniaExiste(claveColonia)) {
                JOptionPane.showMessageDialog(vista, "La colonia no existe");
                return;
            }

            modelo.getUsuarios().add(new Usuario(numero, nombre, direccion, claveColonia, tipo, consumo));
            modelo.guardarDatos();
            actualizarTablaUsuarios();
            actualizarTablaColonias();
            limpiarCamposUsuario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Datos invalidos");
        }
    }

    private void buscarUsuario() {
        try {
            int numero = Integer.parseInt(vista.txtNumUsuario.getText());
            for (Usuario u : modelo.getUsuarios()) {
                if (u.getNumeroUsuario() == numero) {
                    vista.txtNomUsuario.setText(u.getNombre());
                    vista.txtDirUsuario.setText(u.getDireccion());
                    vista.txtClaveCol.setText(u.getClaveColonia());
                    vista.cbTipoUsuario.setSelectedItem(u.getTipoUsuario());
                    vista.txtConsumoUsuario.setText(String.valueOf(u.getConsumo()));
                    return;
                }
            }
            JOptionPane.showMessageDialog(vista, "No encontrado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ingresa un numero");
        }
    }

    private void actualizarUsuario() {
        try {
            int numero = Integer.parseInt(vista.txtNumUsuario.getText());
            String claveColonia = vista.txtClaveCol.getText();
            double consumo = Double.parseDouble(vista.txtConsumoUsuario.getText());

            if (!validarColoniaExiste(claveColonia)) {
                JOptionPane.showMessageDialog(vista, "La colonia no existe");
                return;
            }

            for (Usuario u : modelo.getUsuarios()) {
                if (u.getNumeroUsuario() == numero) {
                    u.setNombre(vista.txtNomUsuario.getText());
                    u.setDireccion(vista.txtDirUsuario.getText());
                    u.setClaveColonia(claveColonia);
                    u.setTipoUsuario((int) vista.cbTipoUsuario.getSelectedItem());
                    u.setConsumo(consumo);
                    modelo.guardarDatos();
                    actualizarTablaUsuarios();
                    actualizarTablaColonias();
                    JOptionPane.showMessageDialog(vista, "Actualizado");
                    return;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al actualizar");
        }
    }

    private void eliminarUsuario() {
        try {
            int numero = Integer.parseInt(vista.txtNumUsuario.getText());
            if (modelo.getUsuarios().removeIf(u -> u.getNumeroUsuario() == numero)) {
                modelo.guardarDatos();
                actualizarTablaUsuarios();
                actualizarTablaColonias();
                limpiarCamposUsuario();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al eliminar");
        }
    }

    private boolean validarColoniaExiste(String clave) {
        for (Colonia c : modelo.getColonias()) {
            if (c.getClaveColonia().equals(clave)) return true;
        }
        return false;
    }

    private void limpiarCamposUsuario() {
        vista.txtNumUsuario.setText("");
        vista.txtNomUsuario.setText("");
        vista.txtDirUsuario.setText("");
        vista.txtClaveCol.setText("");
        vista.txtConsumoUsuario.setText("");
    }

    private void guardarColonia() {
        String clave = vista.txtClaveColonia.getText();
        String nombre = vista.txtNombreColonia.getText();
        if (clave.isEmpty() || nombre.isEmpty()) return;

        for (Colonia c : modelo.getColonias()) {
            if (c.getClaveColonia().equals(clave)) {
                JOptionPane.showMessageDialog(vista, "La clave ya existe");
                return;
            }
        }

        modelo.getColonias().add(new Colonia(clave, nombre));
        modelo.guardarDatos();
        actualizarTablaColonias();
        vista.txtClaveColonia.setText("");
        vista.txtNombreColonia.setText("");
    }

    private void buscarColonia() {
        String clave = vista.txtClaveColonia.getText();
        for (Colonia c : modelo.getColonias()) {
            if (c.getClaveColonia().equals(clave)) {
                vista.txtNombreColonia.setText(c.getNombreColonia());
                return;
            }
        }
    }

    private void actualizarColonia() {
        String clave = vista.txtClaveColonia.getText();
        for (Colonia c : modelo.getColonias()) {
            if (c.getClaveColonia().equals(clave)) {
                c.setNombreColonia(vista.txtNombreColonia.getText());
                modelo.guardarDatos();
                actualizarTablaColonias();
                actualizarTablaUsuarios();
                return;
            }
        }
    }

    private void eliminarColonia() {
        String clave = vista.txtClaveColonia.getText();
        for (Usuario u : modelo.getUsuarios()) {
            if (u.getClaveColonia().equals(clave)) {
                JOptionPane.showMessageDialog(vista, "Colonia en uso");
                return;
            }
        }
        if (modelo.getColonias().removeIf(c -> c.getClaveColonia().equals(clave))) {
            modelo.guardarDatos();
            actualizarTablaColonias();
        }
    }

    private void actualizarTarifa() {
        try {
            int tipo = (int) vista.cbTipoTarif.getSelectedItem();
            double monto = Double.parseDouble(vista.txtMontoTarif.getText());
            modelo.getTarifas().setTarifa(tipo, monto);
            modelo.guardarDatos();
            actualizarTablaTarifas();
            actualizarTablaUsuarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Monto invalido");
        }
    }

    private void actualizarTablaUsuarios() {
        vista.modUsuarios.setRowCount(0);
        double total = 0;
        for (Usuario u : modelo.getUsuarios()) {
            String colNom = "N/A";
            for (Colonia c : modelo.getColonias()) {
                if (c.getClaveColonia().equals(u.getClaveColonia())) {
                    colNom = c.getNombreColonia();
                    break;
                }
            }
            double tarifaBase = modelo.getTarifas().getTarifa(u.getTipoUsuario());
            double montoFinal = tarifaBase * u.getConsumo();
            total += montoFinal;

            vista.modUsuarios.addRow(new Object[]{
                    u.getNumeroUsuario(),
                    u.getNombre(),
                    colNom,
                    u.getTipoUsuario(),
                    String.format("%.2f", u.getConsumo()),
                    String.format("%.2f", montoFinal)
            });
        }
        vista.lblTotalGeneral.setText("TOTAL GENERAL: " + String.format("%.2f", total));
    }

    private void actualizarTablaColonias() {
        vista.modColonias.setRowCount(0);
        for (Colonia c : modelo.getColonias()) {
            int count = 0;
            for (Usuario u : modelo.getUsuarios()) {
                if (u.getClaveColonia().equals(c.getClaveColonia())) count++;
            }
            vista.modColonias.addRow(new Object[]{c.getClaveColonia(), c.getNombreColonia(), count});
        }
    }

    private void actualizarTablaTarifas() {
        vista.modTarifas.setRowCount(0);
        double[] t = modelo.getTarifas().getArregloTarifas();
        for (int i = 0; i < t.length; i++) {
            vista.modTarifas.addRow(new Object[]{i + 1, String.format("%.2f", t[i])});
        }
    }
}