package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;

public class CarritoAnadirView extends JInternalFrame {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnBuscar2;
    private JTextField txtSubTotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnAñadir;
    private JComboBox cbxCantidad;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel LblPrecio;
    private JLabel lblCantidad;
    private JLabel lblSubtotal;
    private JLabel lblIVA;
    private JLabel lblTotal;
    private JLabel lblAñadirCarrito;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeI;

    public CarritoAnadirView(MensajeInternacionalizacionHandler mensajeI) {
        super("Carrito de Compras", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        this.mensajeI = mensajeI;
        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatos();
        cambiarIdi();
        iconoIma();

    }
    public void cambiarIdi(){
        setTitle(mensajeI.get("carrito.anadir.titulo"));
        lblCodigo.setText(mensajeI.get("carrito.anadir.codigo"));
        lblCantidad.setText(mensajeI.get("carrito.anadir.cantidad"));
        lblNombre.setText(mensajeI.get("carrito.anadir.nombre"));
        lblSubtotal.setText(mensajeI.get("carrito.anadir.subtotal"));
        lblIVA.setText(mensajeI.get("carrito.anadir.iva"));
        lblTotal.setText(mensajeI.get("carrito.anadir.total"));

        btnAñadir.setText(mensajeI.get("carrito.anadir.boton.anadir"));
        btnGuardar.setText(mensajeI.get("carrito.anadir.boton.guardar"));
        btnBuscar2.setText(mensajeI.get("carrito.anadir.boton.buscar"));
        btnLimpiar.setText(mensajeI.get("carrito.anadir.boton.limpiar"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeI.get("carrito.anadir.tabla.codigo"),
                mensajeI.get("carrito.anadir.tabla.nombre"),
                mensajeI.get("carrito.anadir.tabla.precio"),
                mensajeI.get("carrito.anadir.tabla.cantidad"),
                mensajeI.get("carrito.anadir.tabla.subtotal")
        });
    }
    private void cargarDatos(){
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JButton getBtnBuscar2() {
        return btnBuscar2;
    }

    public void setBtnBuscar2(JButton btnBuscar2) {
        this.btnBuscar2 = btnBuscar2;
    }

    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }

    public void setTxtSubTotal(JTextField txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public void setBtnAñadir(JButton btnAñadir) {
        this.btnAñadir = btnAñadir;
    }

    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    public void setCbxCantidad(JComboBox cbxCantidad) {
        this.cbxCantidad = cbxCantidad;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public MensajeInternacionalizacionHandler getMensajeI() {
        return mensajeI;
    }

    public void setMensajeI(MensajeInternacionalizacionHandler mensajeI) {
        this.mensajeI = mensajeI;
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtIva.setText("");
        txtSubTotal.setText("");
        txtTotal.setText("");
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    private void iconoIma(){
        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btBuscar);
            btnBuscar2.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }

        URL btAnadir = LoginView.class.getClassLoader().getResource("imagenes/anadircarrito.png");
        if (btAnadir != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btAnadir);
            btnAñadir.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Añadir");
        }

        URL btGuardar = LoginView.class.getClassLoader().getResource("imagenes/agregarcarrito.png");
        if (btGuardar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btGuardar);
            btnGuardar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Guardar");
        }

        URL btLimpiar = LoginView.class.getClassLoader().getResource("imagenes/limpiar.png");
        if (btLimpiar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btLimpiar);
            btnLimpiar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Limpiar");
        }
    }
}
