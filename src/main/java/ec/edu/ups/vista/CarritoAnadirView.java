package ec.edu.ups.vista;

import javax.swing.*;

public class CarritoAnadirView extends JInternalFrame {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnBuscar;
    private JTable table1;
    private JTextField txtSubTotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnAñadir;
    private JComboBox cbxCantidad;
    private JScrollPane tblProductos;
    private JPanel panelPrincipal;
    private JButton guardarButton;
    private JButton limpiarButton;

    public CarritoAnadirView() {
        super("Carrito de Compras",true,true,true,true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        cargarDatos();
    }
    private void cargarDatos() {
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20;i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }
}
