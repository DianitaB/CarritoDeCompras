package ec.edu.ups.vista.producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

public class ProductoAnadirView extends JInternalFrame  {

    private MensajeInternacionalizacionHandler mensajeI;
    private JPanel panelPrincipal;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblProducto;
    private JPanel pnlCodigo;
    private JPanel pnlNombre;
    private JPanel pnlPrecio;

    public ProductoAnadirView(MensajeInternacionalizacionHandler mensajeI) {

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        //setLocationRelativeTo(null);
       // setVisible(true);
        //pack();
        this.mensajeI = mensajeI;

        URL btAceptar = LoginView.class.getClassLoader().getResource("imagenes/aceptar.png");
        if (btAceptar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btAceptar);
            btnAceptar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Aceptar");
        }

        URL btLimpiar = LoginView.class.getClassLoader().getResource("imagenes/limpiar.png");
        if (btLimpiar != null) {
            ImageIcon iconBtnLimpiar = new ImageIcon(btLimpiar);
            btnLimpiar.setIcon(iconBtnLimpiar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Limpiar");
        }

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {limpiarCampos(); }
        });
        cambiarIdi();
    }
    
    public void cambiarIdi() {
        setTitle(mensajeI.get("producto.crear.titulo"));
        lblProducto.setText(mensajeI.get("producto.label.productos"));
        lblCodigo.setText(mensajeI.get("producto.label.codigo"));
        lblNombre.setText(mensajeI.get("producto.label.nombre"));
        lblPrecio.setText(mensajeI.get("producto.label.precio"));
        btnAceptar.setText(mensajeI.get("producto.boton.aceptar"));
        btnLimpiar.setText(mensajeI.get("producto.boton.limpiar"));
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

}
