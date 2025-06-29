package ec.edu.ups.vista;

import javax.swing.*;

public class RegistrarseView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JPasswordField txtContrasenia;
    private JPasswordField txtConfirmarContrasenia;
    private JButton btnRegistrarse2;


    public RegistrarseView() {
        setContentPane(panelPrincipal);
        setTitle("Registrarse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 190);
        setLocationRelativeTo(null);
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JPasswordField getTxtConfirmarContrasenia() {
        return txtConfirmarContrasenia;
    }

    public void setTxtConfirmarContrasenia(JPasswordField txtConfirmarContrasenia) {
        this.txtConfirmarContrasenia = txtConfirmarContrasenia;
    }

    public JButton getBtnRegistrarse2() {
        return btnRegistrarse2;
    }

    public void setBtnRegistrarse2(JButton btnRegistrarse2) {
        this.btnRegistrarse2 = btnRegistrarse2;
    }
    public String getContraseniaComoTexto() {
        return new String(txtContrasenia.getPassword());
    }

    public String getConfirmarContraseniaComoTexto() {
        return new String(txtConfirmarContrasenia.getPassword());
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtConfirmarContrasenia.setText("");
    }

}
