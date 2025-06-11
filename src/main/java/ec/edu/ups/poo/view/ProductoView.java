package ec.edu.ups.poo.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JComboBox cbCategoria;
    private JTextPane txtDetalles;
    private JButton btnRegresar;
    private JButton BtnGuardar;
    private JButton BtnSalir;

    public ProductoView() {
        setContentPane(panelPrincipal);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        BtnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatos();
            }
        });
    }

    private void mostrarDatos() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        String detalles = txtDetalles.getText();
        System.out.println(id);
        System.out.println(nombre);
        System.out.println(cantidad);
        System.out.println(detalles);
    }


    public static void main(String[] args) {
        new ProductoView();
    }

}
