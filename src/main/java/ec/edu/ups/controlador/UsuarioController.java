package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LoginView;
import ec.edu.ups.vista.MenuPrincipalView;
import ec.edu.ups.vista.RegistrarseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {

    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private RegistrarseView registrarseView;
    private MenuPrincipalView menuPrincipalView;


    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, RegistrarseView registrarseView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuario = null;
        this.registrarseView = registrarseView;
        configurarEventosEnVistas();
    }
    public void setMenuPrincipalView(MenuPrincipalView menuPrincipalView) {
        this.menuPrincipalView = menuPrincipalView;
        configurarEventoCerrarSesion();
    }
    public void setRegistrarseView(RegistrarseView registrarseView) {
        this.registrarseView = registrarseView;
        configuracionEventosEnRegistrarse();
    }
    private void configurarEventoCerrarSesion(){
        menuPrincipalView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }
    private void configuracionEventosEnRegistrarse() {
        registrarseView.getBtnRegistrarse2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crear();

            }
        });
    }

    private void configurarEventosEnVistas(){
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
        loginView.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarseView = new RegistrarseView();
                setRegistrarseView(registrarseView);
                registrarseView.setVisible(true);
            }
        });

    }
    public void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(
                menuPrincipalView,
                "¿Está seguro que desea cerrar sesión?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            if (menuPrincipalView != null) {
                menuPrincipalView.setVisible(false);
                menuPrincipalView = null;
            }
            if (loginView != null) {
                loginView.limpiarCampos();
                loginView.setVisible(true);
            }
            this.usuario = null;
        }
    }

    private void crear(){
        String usuarioT = registrarseView.getTxtUsuario().getText();
        String contrasenia = new String(registrarseView.getTxtContrasenia().getPassword());
        String confirmarContrasenia = new String(registrarseView.getTxtConfirmarContrasenia().getPassword());

        if (usuarioT.isEmpty() || contrasenia.isEmpty() || confirmarContrasenia.isEmpty()) {
            registrarseView.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }
        if (!contrasenia.equals(confirmarContrasenia)) {
            registrarseView.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }
        Usuario nuevoUsuario = new Usuario(usuarioT,contrasenia);
        usuarioDAO.crear(nuevoUsuario);
        registrarseView.mostrarMensaje("Usuario registrado con éxito.");
        registrarseView.dispose();
    }

    private void autenticar(){
        String username = loginView.getTxtUsername().getText();
        String contrasenia = new String( loginView.getTxtContrasenia().getPassword());

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if(usuario == null){
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        }else{
            loginView.mostrarMensaje("Bienvenido al sistema: " + loginView.getTxtUsername().getText());
            loginView.dispose();
        }
    }
    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
}