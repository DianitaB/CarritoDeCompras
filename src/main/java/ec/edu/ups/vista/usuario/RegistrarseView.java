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
    private JPanel panelConf;
    private JButton btnGuardarRespuestas;
    private JLabel lblConfContra;
    private JLabel lblRegistarse;
    private JTextField txtCedula;
    private JLabel lblCedula;
    private MensajeInternacionalizacionHandler mensajeI;


    public RegistrarseView(MensajeInternacionalizacionHandler mensajeI) {
        setContentPane(panelPrincipal);
        setTitle("Registrarse");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        this.mensajeI = mensajeI;
        iconoIma();
        cargarPreguntas();
        cambiarIdioma();
    }
    public JTextField getTxtNombresCom() {
        return txtNombresCom;
    }

    public JTextField getTxtFNacimiento() {
        return txtFNacimiento;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtResp1() {
        return txtResp1;
    }

    public JComboBox getCbxP1() {
        return cbxP1;
    }

    public JComboBox getCbxPre3() {
        return cbxPre3;
    }

    public JComboBox getCbxP2() {
        return cbxP2;
    }

    public JTextField getTxtResp2() {
        return txtResp2;
    }

    public JTextField getTxtResp3() {
        return txtResp3;
    }
    public String getUsuario() {
        return txtUsuario.getText().trim();
    }

    public String getCedula() {
        return txtCedula.getText().trim();
    }

    public String getContrasenia() {
        return new String(txtContrasenia.getPassword()).trim();
    }

    public String getConfirmarContrasenia() {
        return new String(txtConfirmarContrasenia.getPassword()).trim();
    }

    public String getNombreCompleto() {
        return txtNombresCom.getText().trim();
    }

    public String getFechaNacimiento() {
        return txtFNacimiento.getText().trim();
    }


    public String getCorreo() {
        return txtCorreo.getText().trim();
    }

    public String getTelefono() {
        return txtTelefono.getText().trim();
    }

    public String getPregunta1() {
        return (String) cbxP1.getSelectedItem();
    }

    public String getPregunta2() {
        return (String) cbxP2.getSelectedItem();
    }

    public String getPregunta3() {
        return (String) cbxPre3.getSelectedItem();
    }

    public String getRespuesta1() {
        return txtResp1.getText().trim();
    }

    public String getRespuesta2() {
        return txtResp2.getText().trim();
    }

    public String getRespuesta3() {
        return txtResp3.getText().trim();
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

    public JPanel getPanelConf() {
        return panelConf;
    }

    public void setPanelConf(JPanel panelConf) {
        this.panelConf = panelConf;
    }

    public JLabel getLblCedula() {
        return lblCedula;
    }

    public void setLblCedula(JLabel lblCedula) {
        this.lblCedula = lblCedula;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    public JLabel getLblRegistarse() {
        return lblRegistarse;
    }

    public void setLblRegistarse(JLabel lblRegistarse) {
        this.lblRegistarse = lblRegistarse;
    }

    public JLabel getLblConfContra() {
        return lblConfContra;
    }

    public void setLblConfContra(JLabel lblConfContra) {
        this.lblConfContra = lblConfContra;
    }

    public JButton getBtnGuardarRespuestas() {
        return btnGuardarRespuestas;
    }

    public void setBtnGuardarRespuestas(JButton btnGuardarRespuestas) {
        this.btnGuardarRespuestas = btnGuardarRespuestas;
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
    public void cambiarIdioma() {
        this.setTitle(mensajeI.get("registro.titulo.ventana"));
        lblRegistarse.setText(mensajeI.get("registro.titulo.vew"));
        lblUsuario.setText(mensajeI.get("registro.label.usuario"));
        lblContrasenia.setText(mensajeI.get("registro.label.contrasenia"));
        lblConfContra.setText(mensajeI.get("registro.label.confirmar"));
        lblNombres.setText(mensajeI.get("registro.label.nombres"));
        lblFechaNa.setText(mensajeI.get("registro.label.fechaNacimiento"));
        lblCorreo.setText(mensajeI.get("registro.label.correo"));
        lblTelefono.setText(mensajeI.get("registro.label.telefono"));
        lblPreguntas.setText(mensajeI.get("registro.label.preguntas"));
        lblPre1.setText(mensajeI.get("registro.label.pregunta1"));
        lblResp1.setText(mensajeI.get("registro.label.respuesta1"));
        lblPre2.setText(mensajeI.get("registro.label.pregunta2"));
        lblResp2.setText(mensajeI.get("registro.label.respuesta2"));
        lblPre3.setText(mensajeI.get("registro.label.pregunta3"));
        lblResp3.setText(mensajeI.get("registro.label.respuesta3"));
        btnRegistrarse2.setText(mensajeI.get("registro.boton.registrarse"));
        btnGuardarRespuestas.setText(mensajeI.get("registro.boton.guardarRespuestas"));
    }
    private void cargarPreguntas() {
        String[] preguntas = {
                mensajeI.get("pregunta.colorFavorito"),
                mensajeI.get("pregunta.nombrePrimeraMascota"),
                mensajeI.get("pregunta.comidaFavorita"),
                mensajeI.get("pregunta.cancionFavorita"),
                mensajeI.get("pregunta.ciudadNacimiento"),
                mensajeI.get("pregunta.peliculaFavorita"),
                mensajeI.get("pregunta.mejorAmigoInfancia"),
                mensajeI.get("pregunta.deporteFavorito"),
                mensajeI.get("pregunta.paisVisitar"),
                mensajeI.get("pregunta.profesorFavorito")
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
