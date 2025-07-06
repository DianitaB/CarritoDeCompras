package ec.edu.ups.vista.carrito;


import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;

public class CarritoListarView  extends JInternalFrame{
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTable tblPCarrito;
    private JPanel panelPrincipal;
    private JTextField txtTotal;
    private JLabel lblCarrito;
    private JLabel lblTotal;
    DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeI;

    public CarritoListarView(MensajeInternacionalizacionHandler mensajeI) {
        super("Listado de Carritos", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblPCarrito.setModel(modelo);
        this.mensajeI = mensajeI;
        iconoIma();
        cambiarIdioma();

    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTable getTblPCarrito() {
        return tblPCarrito;
    }

    public void setTblPCarrito(JTable tblPCarrito) {
        this.tblPCarrito = tblPCarrito;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void cargarCarrito(){}
    public void cambiarIdioma() {
        this.setTitle(mensajeI.get("carritoListar.titulo.ventana"));

        lblCarrito.setText(mensajeI.get("carritoListar.label.carrito"));
        lblTotal.setText(mensajeI.get("carritoListar.label.total"));

        btnBuscar.setText(mensajeI.get("carritoListar.boton.buscar"));
        btnListar.setText(mensajeI.get("carritoListar.boton.listar"));

        if (modelo != null) {
            modelo.setColumnIdentifiers(new String[] {
                    mensajeI.get("carritoListar.columna.codigo"),
                    mensajeI.get("carritoListar.columna.nombre"),
                    mensajeI.get("carritoListar.columna.precio"),
                    mensajeI.get("carritoListar.columna.cantidad"),
                    mensajeI.get("carritoListar.columna.total")
            });
        }
    }
    private void iconoIma() {
        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btBuscar);
            btnBuscar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }

        URL btListar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if (btListar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btListar);
            btnListar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Listar");
        }
    }
}
