package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class RegistrarseView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JPasswordField txtContrasenia;
    private JPasswordField txtConfirmarContrasenia;
    private JButton btnRegistrarse2;
    private JTextField txtNombresCom;
    private JLabel lblNombres;
    private JTextField txtFNacimiento;
    private JLabel lblFechaNa;
    private JTextField txtCorreo;
    private JLabel lblCorreo;
    private JTextField txtTelefono;
    private JLabel lblTelefono;
    private JLabel lblPreguntas;
    private JComboBox cbxP1;
    private JTextField txtResp1;
    private JLabel lblResp1;
    private JLabel lblPre1;
    private JComboBox cbxP2;
    private JComboBox cbxPre3;
    private JTextField txtResp2;
    private JTextField txtResp3;
    private JLabel lblPre2;
    private JLabel lblResp2;
    private JLabel lblPre3;
    private JLabel lblResp3;
    private JLabel lblUsuario;
    private JLabel lblContrasenia;
    private JPanel lblConfContrasenia;
    private MensajeInternacionalizacionHandler mensajeI;


    public RegistrarseView(MensajeInternacionalizacionHandler mensajeI) {
        setContentPane(panelPrincipal);
        setTitle("Registrarse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        this.mensajeI = mensajeI;
        iconoIma();
        cargarPreguntas();
    }



    public JTextField getTxtNombresCom() {
        return txtNombresCom;
    }

    public void setTxtNombresCom(JTextField txtNombresCom) {
        this.txtNombresCom = txtNombresCom;
    }

    public JLabel getLblNombres() {
        return lblNombres;
    }

    public void setLblNombres(JLabel lblNombres) {
        this.lblNombres = lblNombres;
    }

    public JTextField getTxtFNacimiento() {
        return txtFNacimiento;
    }

    public void setTxtFNacimiento(JTextField txtFNacimiento) {
        this.txtFNacimiento = txtFNacimiento;
    }

    public JLabel getLblFechaNa() {
        return lblFechaNa;
    }

    public void setLblFechaNa(JLabel lblFechaNa) {
        this.lblFechaNa = lblFechaNa;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }

    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public JLabel getLblPreguntas() {
        return lblPreguntas;
    }

    public void setLblPreguntas(JLabel lblPreguntas) {
        this.lblPreguntas = lblPreguntas;
    }

    public JTextField getTxtResp1() {
        return txtResp1;
    }

    public void setTxtResp1(JTextField txtResp1) {
        this.txtResp1 = txtResp1;
    }

    public JComboBox getCbxP1() {
        return cbxP1;
    }

    public void setCbxP1(JComboBox cbxP1) {
        this.cbxP1 = cbxP1;
    }

    public JLabel getLblResp1() {
        return lblResp1;
    }

    public void setLblResp1(JLabel lblResp1) {
        this.lblResp1 = lblResp1;
    }

    public JLabel getLblPre1() {
        return lblPre1;
    }

    public void setLblPre1(JLabel lblPre1) {
        this.lblPre1 = lblPre1;
    }

    public JComboBox getCbxPre3() {
        return cbxPre3;
    }

    public void setCbxPre3(JComboBox cbxPre3) {
        this.cbxPre3 = cbxPre3;
    }

    public JComboBox getCbxP2() {
        return cbxP2;
    }

    public void setCbxP2(JComboBox cbxP2) {
        this.cbxP2 = cbxP2;
    }

    public JTextField getTxtResp2() {
        return txtResp2;
    }

    public void setTxtResp2(JTextField txtResp2) {
        this.txtResp2 = txtResp2;
    }

    public JTextField getTxtResp3() {
        return txtResp3;
    }

    public void setTxtResp3(JTextField txtResp3) {
        this.txtResp3 = txtResp3;
    }

    public JLabel getLblPre2() {
        return lblPre2;
    }

    public void setLblPre2(JLabel lblPre2) {
        this.lblPre2 = lblPre2;
    }

    public JLabel getLblResp2() {
        return lblResp2;
    }

    public void setLblResp2(JLabel lblResp2) {
        this.lblResp2 = lblResp2;
    }

    public JLabel getLblPre3() {
        return lblPre3;
    }

    public void setLblPre3(JLabel lblPre3) {
        this.lblPre3 = lblPre3;
    }

    public JLabel getLblResp3() {
        return lblResp3;
    }

    public void setLblResp3(JLabel lblResp3) {
        this.lblResp3 = lblResp3;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JLabel getLblContrasenia() {
        return lblContrasenia;
    }

    public void setLblContrasenia(JLabel lblContrasenia) {
        this.lblContrasenia = lblContrasenia;
    }

    public JPanel getLblConfContrasenia() {
        return lblConfContrasenia;
    }

    public void setLblConfContrasenia(JPanel lblConfContrasenia) {
        this.lblConfContrasenia = lblConfContrasenia;
    }

    public MensajeInternacionalizacionHandler getMensajeI() {
        return mensajeI;
    }

    public void setMensajeI(MensajeInternacionalizacionHandler mensajeI) {
        this.mensajeI = mensajeI;
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
    private void cargarPreguntas() {
        String[] preguntas = {
                "¿Cuál es tu color favorito?",
                "¿Cuál es el nombre de tu primera mascota?",
                "¿Cuál es tu comida favorita?",
                "¿Cuál es tu canción favorita?",
                "¿En qué ciudad naciste?",
                "¿Cuál es tu película favorita?",
                "¿Cuál es el nombre de tu mejor amigo de la infancia?",
                "¿Cuál es tu deporte favorito?",
                "¿Qué país te gustaría visitar?",
                "¿Cómo se llama tu profesor favorito?"
        };

        for (String p : preguntas) {
            cbxP1.addItem(p);
            cbxP2.addItem(p);
            cbxPre3.addItem(p);
        }
    }
    public void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtConfirmarContrasenia.setText("");
    }
    private void iconoIma(){
        URL btRegistarsee = LoginView.class.getClassLoader().getResource("imagenes/registrar.png");
        if (btRegistarsee != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btRegistarsee);
            btnRegistrarse2.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Validar");
        }

    }
}
