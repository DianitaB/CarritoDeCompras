package ec.edu.ups.vista.producto;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.net.URL;

public class ProductoEliminarView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCodigo;
    private JLabel lblTituloE;
    private MensajeInternacionalizacionHandler mensajeI;

    public ProductoEliminarView(MensajeInternacionalizacionHandler mensajeI) {
        setContentPane(panelPrincipal);
        setTitle("Eliminar Producto");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        this.mensajeI = mensajeI;
        iconoIma();
        cambiarIdioma();

    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
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

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
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
        this.setTitle(mensajeI.get("productoEliminar.titulo.ventana"));
        lblTituloE.setText(mensajeI.get("productoEliminar.label.titulo"));
        lblCodigo.setText(mensajeI.get("productoEliminar.label.codigo"));
        lblNombre.setText(mensajeI.get("productoEliminar.label.nombre"));
        lblPrecio.setText(mensajeI.get("productoEliminar.label.precio"));
        btnBuscar.setText(mensajeI.get("productoEliminar.boton.buscar"));
        btnEliminar.setText(mensajeI.get("productoEliminar.boton.eliminar"));
    }

    private void iconoIma(){
        URL btEliminar = LoginView.class.getClassLoader().getResource("imagenes/eliminar.png");
        if (btEliminar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btEliminar);
            btnEliminar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Eliminar");
        }

        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btBuscar);
            btnBuscar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }
    }
}
