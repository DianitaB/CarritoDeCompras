package ec.edu.ups.vista.preguntas;

import ec.edu.ups.controlador.RecuperacionController;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.net.URL;

public class CuestionarioRecuView extends JInternalFrame {
    private JPasswordField pswNueva;
    private JPasswordField pswConfContra;
    private JButton btnGuardarNueva;
    private JLabel lblConfContra;
    private JLabel lblNuevaContra;
    private JPanel panelPrincipal;
    private MensajeInternacionalizacionHandler mensajeI;
    private RecuperacionController controlador;


    public CuestionarioRecuView(MensajeInternacionalizacionHandler mensajeI) {
        super("Nueva Contraseña", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        this.mensajeI = mensajeI;
        iconoIma();
        cambiarIdioma();
    }

    public JPasswordField getPswNueva() {
        return pswNueva;
    }

    public void setPswNueva(JPasswordField pswNueva) {
        this.pswNueva = pswNueva;
    }

    public JPasswordField getPswConfContra() {
        return pswConfContra;
    }

    public void setPswConfContra(JPasswordField pswConfContra) {
        this.pswConfContra = pswConfContra;
    }

    public JButton getBtnGuardarNueva() {
        return btnGuardarNueva;
    }

    public void setBtnGuardarNueva(JButton btnGuardarNueva) {
        this.btnGuardarNueva = btnGuardarNueva;
    }

    public JLabel getLblConfContra() {
        return lblConfContra;
    }

    public void setLblConfContra(JLabel lblConfContra) {
        this.lblConfContra = lblConfContra;
    }

    public JLabel getLblNuevaContra() {
        return lblNuevaContra;
    }

    public void setLblNuevaContra(JLabel lblNuevaContra) {
        this.lblNuevaContra = lblNuevaContra;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void cambiarIdioma() {
        this.setTitle(mensajeI.get("cuestionarioRecu.titulo.ventana"));
        lblNuevaContra.setText(mensajeI.get("cuestionarioRecu.label.nuevaContrasenia"));
        lblConfContra.setText(mensajeI.get("cuestionarioRecu.label.confirmarContrasenia"));

        btnGuardarNueva.setText(mensajeI.get("cuestionarioRecu.boton.guardar"));
    }

    private void iconoIma(){
        URL btGuardar = LoginView.class.getClassLoader().getResource("imagenes/contrasenia.png");
        if (btGuardar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btGuardar);
            btnGuardarNueva.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Guardar Contraseña");
        }
    }
}

