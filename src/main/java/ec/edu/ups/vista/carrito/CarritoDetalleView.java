package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;

public class CarritoDetalleView extends JInternalFrame {
    private JTextField txtSubtotalDet;
    private JTextField txtIVADet;
    private JTextField txtTotalDet;
    private JTextField txtIdDet;
    private JButton btnBuscarDetalle;
    private JButton btnAceptarDetalle;
    private JTable tblDetCarrito;
    private JPanel panelPrincipal;
    private JLabel lblDetalles;
    private JLabel lblId;
    private JLabel lblSubtotal;
    private JLabel lblIVA;
    private JLabel lblTotal;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeI;

    public CarritoDetalleView(MensajeInternacionalizacionHandler mensajeI) {
        super("Detalle del Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblDetCarrito.setModel(modelo);
        this.mensajeI = mensajeI;
        cambiarIdi();

        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btBuscar);
            btnBuscarDetalle.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar Detalle");
        }

        URL btAceptar = LoginView.class.getClassLoader().getResource("imagenes/aceptar.png");
        if (btAceptar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btAceptar);
            btnAceptarDetalle.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Aceptar Detalle");
        }
    }
    public void cambiarIdi(){
        mensajeI.setLenguaje(mensajeI.getLocale().getLanguage(), mensajeI.getLocale().getCountry());

        setTitle(mensajeI.get("carrito.detalle.titulo.ventana"));
        lblDetalles.setText(mensajeI.get("carrito.detalle.titulo"));
        lblId.setText(mensajeI.get("carrito.detalle.id"));
        lblSubtotal.setText(mensajeI.get("carrito.detalle.subtotal"));
        lblIVA.setText(mensajeI.get("carrito.detalle.iva"));
        lblTotal.setText(mensajeI.get("carrito.detalle.total"));

        Object[] columnas = {
                mensajeI.get("carrito.detalle.columna.codigo"),
                mensajeI.get("carrito.detalle.columna.nombre"),
                mensajeI.get("carrito.detalle.columna.precio"),
                mensajeI.get("carrito.detalle.columna.cantidad"),
                mensajeI.get("carrito.detalle.columna.subtotal")
        };
        modelo.setColumnIdentifiers(columnas);
    }
    public JTextField getTxtSubtotalDet() {
        return txtSubtotalDet;
    }
    public void setTxtSubtotalDet(JTextField txtSubtotalDet) {
        this.txtSubtotalDet = txtSubtotalDet;
    }

    public JTextField getTxtTotalDet() {
        return txtTotalDet;
    }

    public void setTxtTotalDet(JTextField txtTotalDet) {
        this.txtTotalDet = txtTotalDet;
    }

    public JTextField getTxtIVADet() {
        return txtIVADet;
    }

    public void setTxtIVADet(JTextField txtIVADet) {
        this.txtIVADet = txtIVADet;
    }

    public JTextField getTxtIdDet() {
        return txtIdDet;
    }

    public void setTxtIdDet(JTextField txtIdDet) {
        this.txtIdDet = txtIdDet;
    }

    public JButton getBtnBuscarDetalle() {
        return btnBuscarDetalle;
    }

    public void setBtnBuscarDetalle(JButton btnBuscarDetalle) {
        this.btnBuscarDetalle = btnBuscarDetalle;
    }

    public JButton getBtnAceptarDetalle() {
        return btnAceptarDetalle;
    }

    public void setBtnAceptarDetalle(JButton btnAceptarDetalle) {
        this.btnAceptarDetalle = btnAceptarDetalle;
    }

    public JTable getTblDetCarrito() {
        return tblDetCarrito;
    }

    public void setTblDetCarrito(JTable tblDetCarrito) {
        this.tblDetCarrito = tblDetCarrito;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
}

