package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.preguntas.CuestionarioRecuView;
import ec.edu.ups.vista.preguntas.CuestionarioView;
import ec.edu.ups.vista.usuario.LoginView;
import ec.edu.ups.vista.MenuPrincipalView;
import ec.edu.ups.vista.usuario.RegistrarseView;
import ec.edu.ups.vista.usuario.UsuarioEliminarView;
import ec.edu.ups.vista.usuario.UsuarioListarView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private RegistrarseView registrarseView;
    private MenuPrincipalView menuPrincipalView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioListarView usuarioListarView;
    private MensajeInternacionalizacionHandler mensajeI;
    private CuestionarioView cuestionarioView;
    private PreguntasController preguntasController;


    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, RegistrarseView registrarseView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuario = null;
        this.registrarseView = registrarseView;
        configurarEventosEnVistas();
    }
    public void setPreguntasDependencias(CuestionarioView cuestionarioView, CuestionarioRecuView cuestionarioRecuView, PreguntasDAO preguntasDAO, MensajeInternacionalizacionHandler mensajeI,UsuarioController usuarioController) {
        this.cuestionarioView = cuestionarioView;
        this.preguntasController = new PreguntasController(cuestionarioView, cuestionarioRecuView, preguntasDAO,mensajeI,usuarioController);
        cuestionarioView.setControlador(preguntasController);
        configurarEventoOlvidoContrasena();
    }

    public void setUsuarioEliminarView(UsuarioEliminarView usuarioEliminarView) {
        this.usuarioEliminarView = usuarioEliminarView;
        configurarEventosEliminar();
    }
    public void setMenuPrincipalView(MenuPrincipalView menuPrincipalView) {
        this.menuPrincipalView = menuPrincipalView;
        configurarEventoCerrarSesion();
    }
    public void setRegistrarseView(RegistrarseView registrarseView) {
        this.registrarseView = registrarseView;
        configuracionEventosEnRegistrarse();
    }
    public void setUsuarioListarView(UsuarioListarView usuarioListarView) {
        this.usuarioListarView = usuarioListarView;
        configurarEventosListarUsuario();
    }
    private void configurarEventoOlvidoContrasena() {
        loginView.getBtnOlvidarContra().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cuestionarioView != null) {
                    cuestionarioView.setVisible(true);
                } else {
                    System.out.println("cuestionarioView es null");
                }
            }
        });
    }
    private void configurarEventosListarUsuario() {
        usuarioListarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarios();
            }
        });
        usuarioListarView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarios();
            }
        });
    }

    private void configurarEventosEliminar() {
        usuarioEliminarView.getBtnBuscarE().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
        usuarioEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
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
                registrarseView = new RegistrarseView(mensajeI);
                setRegistrarseView(registrarseView);
                registrarseView.setVisible(true);
            }
        });

    }
    private void buscarUsuarios() {
        String nombre = usuarioListarView.getTxtNombreUsu().getText().trim();
        DefaultTableModel modelo = usuarioListarView.getModelo();
        modelo.setRowCount(0);

        if (nombre.isEmpty()) {
            usuarioListarView.mostrarMensaje("Ingrese un nombre de usuario para buscar.");
            return;
        }
        Usuario usuario = usuarioDAO.buscarPorUsername(nombre);
        if (usuario != null) {
            modelo.addRow(new Object[]{usuario.getUsername(), usuario.getRol().name()});
        } else {
            usuarioListarView.mostrarMensaje("Usuario no encontrado.");
        }
    }
    private void listarUsuarios() {
        List<Usuario> lista = usuarioDAO.listarTodos();
        DefaultTableModel modelo = usuarioListarView.getModelo();
        modelo.setRowCount(0);
        for (Usuario u : lista) {
            modelo.addRow(new Object[]{u.getUsername(), u.getRol().name()});
        }
    }
    public void buscarUsuario(){
        String usuario = usuarioEliminarView.getTxtUsuario().getText();
        Usuario usuariofound = usuarioDAO.buscarPorUsername(usuario);
        if(usuariofound == null){
            usuarioEliminarView.mostrarMensaje("Usuario no encontrado.");
        }
    }
    public void eliminarUsuario(){
        String usernameEliminar = usuarioEliminarView.getTxtUsuario().getText();
        String contraseniaAdmin = usuarioEliminarView.getTxtContraseniaE().getText();

        if (getUsuarioAutenticado() == null || getUsuarioAutenticado().getRol() != Rol.ADMINISTRADOR) {
            usuarioEliminarView.mostrarMensaje("No tienes permisos para eliminar usuarios.");
            return;
        }
        if (!getUsuarioAutenticado().getContrasenia().equals(contraseniaAdmin)) {
            usuarioEliminarView.mostrarMensaje("Contraseña incorrecta.");
            return;
        }
        Usuario usuarioAEliminar = usuarioDAO.buscarPorUsername(usernameEliminar);
        if (usuarioAEliminar.getUsername().equals(getUsuarioAutenticado().getUsername())) {
            usuarioEliminarView.mostrarMensaje("No puedes eliminar tu propia cuenta.");
            return;
        }
        usuarioDAO.eliminar(usernameEliminar);
        usuarioEliminarView.mostrarMensaje("Usuario eliminado con éxito.");
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
                loginView.setVisible(true);
            }
            this.usuario = null;
        }
    }

    private void crear(){
        String usuarioT = registrarseView.getTxtUsuario().getText();
        String contrasenia = new String(registrarseView.getTxtContrasenia().getPassword());
        String confirmarContrasenia = new String(registrarseView.getTxtConfirmarContrasenia().getPassword());
        String nombre = registrarseView.getTxtNombresCom().getText();
        String fechaNac = registrarseView.getTxtFNacimiento().getText();
        String correo = registrarseView.getTxtCorreo().getText();
        String telefono = registrarseView.getTxtTelefono().getText();

        String pregunta1 = (String) registrarseView.getCbxP1().getSelectedItem();
        String pregunta2 = (String) registrarseView.getCbxP2().getSelectedItem();
        String pregunta3 = (String) registrarseView.getCbxPre3().getSelectedItem();

        String respuesta1 = registrarseView.getTxtResp1().getText();
        String respuesta2 = registrarseView.getTxtResp2().getText();
        String respuesta3 = registrarseView.getTxtResp3().getText();

        // Validaciones
        if (usuarioT.isEmpty() || contrasenia.isEmpty() || confirmarContrasenia.isEmpty() || nombre.isEmpty()
                || fechaNac.isEmpty() || correo.isEmpty() || telefono.isEmpty()
                || respuesta1.isEmpty() || respuesta2.isEmpty() || respuesta3.isEmpty()) {
            registrarseView.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        if (!contrasenia.equals(confirmarContrasenia)) {
            registrarseView.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }

        if (pregunta1.equals(pregunta2) || pregunta1.equals(pregunta3) || pregunta2.equals(pregunta3)) {
            registrarseView.mostrarMensaje("Las preguntas deben ser distintas.");
            return;
        }

        if (usuarioDAO.buscarPorUsername(usuarioT) != null) {
            registrarseView.mostrarMensaje("Ya existe un usuario con ese nombre.");
            registrarseView.limpiarCampos();
            return;
        }

        List<String> preguntas = new ArrayList<>();
        preguntas.add(pregunta1);
        preguntas.add(pregunta2);
        preguntas.add(pregunta3);

        List<String> respuestas = new ArrayList<>();
        respuestas.add(respuesta1);
        respuestas.add(respuesta2);
        respuestas.add(respuesta3);

        Usuario nuevoUsuario = new Usuario(usuarioT, contrasenia, nombre, fechaNac, correo, telefono, preguntas, respuestas);
        usuarioDAO.crear(nuevoUsuario);
        registrarseView.mostrarMensaje("Usuario registrado con éxito.");
        registrarseView.dispose();
    }


    private void autenticar(){
        String username = loginView.getTxtUsername().getText();
        String contrasenia = new String(loginView.getTxtContrasenia().getPassword());

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if(usuario == null){
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        }else{
            loginView.mostrarMensaje("Bienvenido al sistema: " + username);
            loginView.dispose();
        }
    }
    public void mostrarVentanaInterna(JInternalFrame frame) {
        if (menuPrincipalView != null) {
            menuPrincipalView.getjDesktopPane().add(frame);
            frame.setVisible(true);
            frame.toFront();
        }
    }


    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
}