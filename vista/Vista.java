package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;

public class Vista extends JFrame {
    public JPanel panel1;
    public JPanel PanelOpcion;
    public JPanel panelCards;

    public JButton btnIrUsuarios;
    public JButton btnIrColonias;
    public JButton btnIrTarifas;

    public JTextField txtNumUsuario;
    public JTextField txtNomUsuario;
    public JTextField txtDirUsuario;
    public JTextField txtClaveCol;
    public JTextField txtConsumoUsuario;
    public JComboBox<Integer> cbTipoUsuario;
    public JButton btnGuardarUsuario;
    public JButton btnEliminarUsuario;
    public JButton btnActualizarUsuario;
    public JButton btnBuscarUsuario;
    public JTable tbUsuarios;
    public JLabel lblTotalGeneral;

    public JTextField txtClaveColonia;
    public JTextField txtNombreColonia;
    public JButton btnGuardarCol;
    public JButton btnEliminarCol;
    public JButton btnActualizarCol;
    public JButton btnBuscarCol;
    public JTable tbCol;

    public JComboBox<Integer> cbTipoTarif;
    public JTextField txtMontoTarif;
    public JButton btnActualizarTarif;
    public JTable tbTarifas;

    public DefaultTableModel modUsuarios;
    public DefaultTableModel modColonias;
    public DefaultTableModel modTarifas;

    public Vista() {
        setTitle("Sistema de Agua Potable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 700);
        setContentPane(panel1);
        setLocationRelativeTo(null);

        modUsuarios = new DefaultTableModel(new String[]{"NÚMERO", "NOMBRE", "COLONIA", "TIPO", "CONSUMO", "MONTO"}, 0);
        modColonias = new DefaultTableModel(new String[]{"CLAVE", "NOMBRE", "USUARIOS"}, 0);
        modTarifas = new DefaultTableModel(new String[]{"TIPO", "TARIFA"}, 0);

        if (tbUsuarios != null) tbUsuarios.setModel(modUsuarios);
        if (tbCol != null) tbCol.setModel(modColonias);
        if (tbTarifas != null) tbTarifas.setModel(modTarifas);
    }
}