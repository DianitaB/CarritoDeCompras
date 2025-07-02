package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import java.net.URL;

import javax.swing.*;
import java.util.Locale;

public class LoginView extends JFrame {
    private JPanel panelPrincipal;
    private JPanel panelSecundario;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JComboBox cbIdiomas;
    private JButton btnOlvidarContra;
    private JLabel lblContrasenia;
    private JLabel lblUsuario;
    private JLabel lblIdioma;
    private MensajeInternacionalizacionHandler mensajeInternalizacion;

    public LoginView(MensajeInternacionalizacionHandler mensajeInternalizacion) {
        this.mensajeInternalizacion = mensajeInternalizacion;
        setTitle("Iniciar Sesión");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        add(panelPrincipal);
        cargarIdiomasEnCombo();
        initComponent();

        URL loginURL = LoginView.class.getClassLoader().getResource("imagenes/login.png");
        if (loginURL != null) {
            ImageIcon iconoBtnIniciarSesion = new ImageIcon(loginURL);
            btnIniciarSesion.setIcon(iconoBtnIniciarSesion);
        } else {
            System.err.println("Error: No se ha cargado el icono de Login");
        }

        URL registrarseURL = LoginView.class.getClassLoader().getResource("imagenes/registrarse.png");
        if (registrarseURL != null) {
            ImageIcon iconoBtnRegistrarse = new ImageIcon(registrarseURL);
            btnRegistrarse.setIcon(iconoBtnRegistrarse);
        } else {
            System.err.println("Error: No se ha cargado el icono de Registrarse");
        }
        URL olvidarURL = LoginView.class.getClassLoader().getResource("imagenes/pregunta.png");
        if (olvidarURL != null) {
            ImageIcon iconoBtnOlvidar = new ImageIcon(olvidarURL);
            btnOlvidarContra.setIcon(iconoBtnOlvidar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Pregunta");
        }
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JPanel getPanelSecundario() {
        return panelSecundario;
    }

    public void setPanelSecundario(JPanel panelSecundario) {
        this.panelSecundario = panelSecundario;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public void setBtnRegistrarse(JButton btnRegistrarse) {
        this.btnRegistrarse = btnRegistrarse;
    }

    public JComboBox getCbIdiomas() {
        return cbIdiomas;
    }

    public void setCbIdiomas(JComboBox cbIdiomas) {
        this.cbIdiomas = cbIdiomas;
    }

    public JButton getBtnOlvidarContra() {
        return btnOlvidarContra;
    }

    public void setBtnOlvidarContra(JButton btnOlvidarContra) {
        this.btnOlvidarContra = btnOlvidarContra;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JLabel getLblIdioma() {
        return lblIdioma;
    }

    public void setLblIdioma(JLabel lblIdioma) {
        this.lblIdioma = lblIdioma;
    }

    public JLabel getLblContrasenia() {
        return lblContrasenia;
    }

    public void setLblContrasenia(JLabel lblContrasenia) {
        this.lblContrasenia = lblContrasenia;
    }

    public MensajeInternacionalizacionHandler getMensajeInternalizacion() {
        return mensajeInternalizacion;
    }

    public void setMensajeInternalizacion(MensajeInternacionalizacionHandler mensajeInternalizacion) {
        this.mensajeInternalizacion = mensajeInternalizacion;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarIdiomasEnCombo() {
        cbIdiomas.removeAllItems();
        cbIdiomas.addItem("Español");
        cbIdiomas.addItem("English");
        cbIdiomas.addItem("Français");
    }
    public void seleccionarIdiomaActual() {
        Locale actual = mensajeInternalizacion.getLocale();
        if (actual.getLanguage().equals("en")) {
            cbIdiomas.setSelectedIndex(1);
        } else if (actual.getLanguage().equals("fr")) {
            cbIdiomas.setSelectedIndex(2);
        } else {
            cbIdiomas.setSelectedIndex(0);
        }
    }
    public void actualizarTextos() {
        this.setTitle(mensajeInternalizacion.get("login.titulo.ventana"));

        lblUsuario.setText(mensajeInternalizacion.get("login.label.usuario"));
        lblContrasenia.setText(mensajeInternalizacion.get("login.label.contrasenia"));
        lblIdioma.setText(mensajeInternalizacion.get("menu.idioma"));

        btnIniciarSesion.setText(mensajeInternalizacion.get("login.boton.iniciar"));
        btnRegistrarse.setText(mensajeInternalizacion.get("login.boton.registrarse"));
        btnOlvidarContra.setText(mensajeInternalizacion.get("login.boton.recuperar"));
    }
    public void initComponent() {
        cargarIdiomasEnCombo();
        seleccionarIdiomaActual();
        actualizarTextos();
    }
}