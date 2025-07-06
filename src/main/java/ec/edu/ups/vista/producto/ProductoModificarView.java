package ec.edu.ups.vista.producto;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.net.URL;

public class ProductoModificarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblModificarProducto;
    private MensajeInternacionalizacionHandler mensajeI;

    public ProductoModificarView(MensajeInternacionalizacionHandler mensajeI) {
        setContentPane(panelPrincipal);
        setTitle("Modificar Producto");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        this.mensajeI = mensajeI;
        cambiarIdioma();
        iconoIma();
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

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
    public void cambiarIdioma() {
        this.setTitle(mensajeI.get("productoModificar.titulo.ventana"));
        lblModificarProducto.setText(mensajeI.get("productoModificar.label.titulo"));
        lblCodigo.setText(mensajeI.get("productoModificar.label.codigo"));
        lblNombre.setText(mensajeI.get("productoModificar.label.nombre"));
        lblPrecio.setText(mensajeI.get("productoModificar.label.precio"));
        btnBuscar.setText(mensajeI.get("productoModificar.boton.buscar"));
        btnModificar.setText(mensajeI.get("productoModificar.boton.modificar"));
    }

    private void iconoIma(){
        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btBuscar);
            btnBuscar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }

        URL btModificar = LoginView.class.getClassLoader().getResource("imagenes/modificar.png");
        if (btModificar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btModificar);
            btnModificar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }
    }
}