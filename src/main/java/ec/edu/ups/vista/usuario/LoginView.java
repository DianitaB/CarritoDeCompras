package ec.edu.ups.vista.usuario;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
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
    private JComboBox cbxTipoA;
    private JLabel lblTipoA;
    private JTextField txtCampoDeRuta;
    private JButton btnBuscarRuta;

    private MensajeInternacionalizacionHandler mensajeInternalizacion;

    private UsuarioDAO usuarioDAO;
    private ProductoDAO productoDAO;
    private CarritoDAO carritoDAO;
    private RecuperacionDAO recuperacionDAO;

    public LoginView(MensajeInternacionalizacionHandler mensajeInternalizacion) {
        this.mensajeInternalizacion = mensajeInternalizacion;

        setTitle("Iniciar Sesión");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setContentPane(panelPrincipal);

        cargarIdiomasEnCombo();
        cargarTipoDeAlmacenamiento();
        seleccionarIdiomaActual();
        cambiarIdioma();
        iconoIma();

        cbIdiomas.addActionListener(e -> {
            String idiomaSeleccionado = (String) cbIdiomas.getSelectedItem();

            switch (idiomaSeleccionado.toLowerCase()) {
                case "english":
                    mensajeInternalizacion.setLenguaje("en", "US");
                    break;
                case "français":
                    mensajeInternalizacion.setLenguaje("fr", "FR");
                    break;
                default:
                    mensajeInternalizacion.setLenguaje("es", "EC");
                    break;
            }

            cambiarIdioma();
        });
    }




    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
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


    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public JComboBox getCbxTipoA() {
        return cbxTipoA;
    }

    public JComboBox getCbIdiomas() {
        return cbIdiomas;
    }

    public JButton getBtnOlvidarContra() {
        return btnOlvidarContra;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public CarritoDAO getCarritoDAO() {
        return carritoDAO;
    }

    public RecuperacionDAO getRecuperacionDAO() {
        return recuperacionDAO;
    }
    public String getTipoAlmacenamiento() {
        return cbxTipoA.getSelectedItem().toString();
    }

    public String getRutaArchivos() {
        return txtCampoDeRuta.getText();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JButton getBtnBuscarRuta() {
        return btnBuscarRuta;
    }

    public void setBtnBuscarRuta(JButton btnBuscarRuta) {
        this.btnBuscarRuta = btnBuscarRuta;
    }

    public void addLoginListener(ActionListener listener) {
        btnIniciarSesion.addActionListener(listener);
    }
    public JTextField getTxtCampoDeRuta() {
        return txtCampoDeRuta;
    }

    public void setTxtCampoDeRuta(JTextField txtCampoDeRuta) {
        this.txtCampoDeRuta = txtCampoDeRuta;
    }

    public void cargarTipoDeAlmacenamiento() {
        cbxTipoA.removeAllItems();
        cbxTipoA.addItem("Memoria");
        cbxTipoA.addItem("Archivo de texto");
        cbxTipoA.addItem("Archivo Binario");
    }

    public void cargarIdiomasEnCombo() {
        cbIdiomas.removeAllItems();
        cbIdiomas.addItem("Español");
        cbIdiomas.addItem("English");
        cbIdiomas.addItem("Français");
    }

    public void seleccionarIdiomaActual() {
        Locale actual = mensajeInternalizacion.getLocale();
        switch (actual.getLanguage()) {
            case "en":
                cbIdiomas.setSelectedIndex(1);
                break;
            case "fr":
                cbIdiomas.setSelectedIndex(2);
                break;
            default:
                cbIdiomas.setSelectedIndex(0);
        }
    }

    public void limpiarCampos() {
        txtUsername.setText("");
        txtContrasenia.setText("");
        cbIdiomas.setSelectedIndex(0);
    }

    public void cambiarIdioma() {
        setTitle(mensajeInternalizacion.get("login.titulo.ventana"));
        lblUsuario.setText(mensajeInternalizacion.get("login.label.usuario"));
        lblContrasenia.setText(mensajeInternalizacion.get("login.label.contrasenia"));
        lblIdioma.setText(mensajeInternalizacion.get("menu.idioma"));
        btnIniciarSesion.setText(mensajeInternalizacion.get("login.boton.iniciar"));
        btnRegistrarse.setText(mensajeInternalizacion.get("login.boton.registrarse"));
        btnOlvidarContra.setText(mensajeInternalizacion.get("login.boton.recuperar"));
    }

    private void iconoIma() {
        URL loginURL = LoginView.class.getClassLoader().getResource("imagenes/login.png");
        if (loginURL != null) {
            btnIniciarSesion.setIcon(new ImageIcon(loginURL));
        }

        URL registrarseURL = LoginView.class.getClassLoader().getResource("imagenes/registrarse.png");
        if (registrarseURL != null) {
            btnRegistrarse.setIcon(new ImageIcon(registrarseURL));
        }

        URL olvidarURL = LoginView.class.getClassLoader().getResource("imagenes/pregunta.png");
        if (olvidarURL != null) {
            btnOlvidarContra.setIcon(new ImageIcon(olvidarURL));
        }
    }
}
