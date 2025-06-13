package ec.edu.ups.poo.view;

import ec.edu.ups.poo.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoView extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lbTitulo;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JPanel panelPrincipall;
    private JButton btnAceptar;
    private JButton btnLimpiar;

    public ProductoView() {
        setContentPane(panelPrincipall);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatos();
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }
    private void mostrarDatos() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        int cantidad = Integer.parseInt(txtPrecio.getText());
        System.out.println(id);
        System.out.println(nombre);
        System.out.println(cantidad);
    }
    public static void main(String[] args) {
        new ProductoView();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
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
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }
    private void createUIComponents() {
        String[] columnas = {"Producto", "Precio U.", "Cantidad", "Subtotal", "Eliminar"};
        Object[][] datos = {};
        JTable tableGeneral = new JTable(new DefaultTableModel(datos, columnas));
    }
}
