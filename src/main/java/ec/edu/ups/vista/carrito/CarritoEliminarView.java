package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoEliminarView extends JInternalFrame {
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTable tblLProductos;
    private JButton btnEliminar;
    private JButton btnVaciar;
    private JPanel panelPrincipal;
    private JLabel lblCodigo;
    private JTextField txtFecha;
    private JLabel lblFecha;
    private MensajeInternacionalizacionHandler mensajeI;
    private DefaultTableModel modelo;

    public CarritoEliminarView(MensajeInternacionalizacionHandler mensajeI) {
        super("Eliminar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblLProductos.setModel(modelo);
        this.mensajeI = mensajeI;
        cambiarIdi();

    }
    public void cambiarIdi(){
        mensajeI.setLenguaje(mensajeI.getLocale().getLanguage(), mensajeI.getLocale().getCountry());
        setTitle(mensajeI.get("carrito.eliminar.titulo"));
        lblCodigo.setText(mensajeI.get("carrito.eliminar.codigo"));
        lblFecha.setText(mensajeI.get("carrito.eliminar.fecha"));

        btnBuscar.setText(mensajeI.get("carrito.eliminar.boton.buscar"));
        btnEliminar.setText(mensajeI.get("carrito.eliminar.boton.eliminar"));
        btnVaciar.setText(mensajeI.get("carrito.eliminar.boton.vaciar"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeI.get("carrito.eliminar.tabla.codigo"),
                mensajeI.get("carrito.eliminar.tabla.nombre"),
                mensajeI.get("carrito.eliminar.tabla.precio"),
                mensajeI.get("carrito.eliminar.tabla.cantidad")
        });
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

    public JButton getBtnVaciar() {
        return btnVaciar;
    }

    public void setBtnVaciar(JButton btnVaciar) {
        this.btnVaciar = btnVaciar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JTable getTblLProductos() {
        return tblLProductos;
    }

    public void setTblLProductos(JTable tblLProductos) {
        this.tblLProductos = tblLProductos;
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
    }
}
