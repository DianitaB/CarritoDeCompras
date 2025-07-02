package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;

public class CarritoModificarView extends JInternalFrame {
    private JTextField txtCodigo;
    private JTextField txtFecha;
    private JButton btnBuscar;
    private JTable tblView;
    private JButton btnModificar;
    private JLabel lblCodigo;
    private JLabel lblFecha;
    private JPanel panelPrincipal;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeI;

    public CarritoModificarView(MensajeInternacionalizacionHandler mensajeI) {
        super("Modificar Carrito",true,true,false,true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblView.setModel(modelo);
        this.mensajeI = mensajeI;
        cambiarIdi();

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
    public void cambiarIdi() {
        mensajeI.setLenguaje(mensajeI.getLocale().getLanguage(),mensajeI.getLocale().getCountry());

        setTitle(mensajeI.get("carrito.titulo.modificar"));
        lblCodigo.setText(mensajeI.get("carrito.label.codigo"));
        lblFecha.setText(mensajeI.get("carrito.label.fecha"));
        btnBuscar.setText(mensajeI.get("carrito.boton.buscar"));
        btnModificar.setText(mensajeI.get("carrito.boton.modificar"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeI.get("carrito.columna.codigo"),
                mensajeI.get("carrito.columna.nombre"),
                mensajeI.get("carrito.columna.precio"),
                mensajeI.get("carrito.columna.cantidad"),
                mensajeI.get("carrito.columna.total")
        });
    }

    public MensajeInternacionalizacionHandler getMensajeI() {
        return mensajeI;
    }

    public void setMensajeI(MensajeInternacionalizacionHandler mensajeI) {
        this.mensajeI = mensajeI;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(JTextField txtFecha) {
        this.txtFecha = txtFecha;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblView() {
        return tblView;
    }

    public void setTblView(JTable tblView) {
        this.tblView = tblView;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JLabel getLblFecha() {
        return lblFecha;
    }

    public void setLblFecha(JLabel lblFecha) {
        this.lblFecha = lblFecha;
    }
}
