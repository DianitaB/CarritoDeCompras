package ec.edu.ups.poo.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoViewl extends JFrame {
    private JPanel panelTitulo;
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JSpinner spCantidad;
    private JLabel lblCantidad;
    private JTextField textField1;
    private JLabel lblPrecio;
    private JButton agregarAlCarritoButton;
    private JTable tableGeneral;
    private JScrollPane scrollPane1;
    private JTextField txtSubTotal;
    private JLabel lblDescuento;
    private JCheckBox marqueCheckBox;
    private JComboBox cbDescuento;
    private JLabel lblDesc;
    private JTextField txtIVA;
    private JTextField txtTotal;
    private JButton btnFinalizar;
    private JPanel panelSuperior;
    private JComboBox cbProducto;
    private JLabel lblProducto;

    public CarritoViewl() {
        setContentPane(panelPrincipal);
        setTitle("Carrito de Compras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        setVisible(true);
    }
    public static void main(String[] args) {
        new CarritoViewl();
    }
    private void createUIComponents() {
        String[] columnas = {"Producto", "Precio U.", "Cantidad", "Subtotal", "Eliminar"};
        Object[][] datos = {};
        tableGeneral = new JTable(new DefaultTableModel(datos, columnas));

        tableGeneral.setShowGrid(true);
        tableGeneral.setGridColor(new java.awt.Color(200, 200, 200));

        scrollPane1 = new JScrollPane(tableGeneral);
    }
}
