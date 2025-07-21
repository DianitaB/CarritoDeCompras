package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.modelo.Exception.CedulaValidationException;
import ec.edu.ups.modelo.Exception.UsuarioException;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.preguntas.CuestionarioRecuView;
import ec.edu.ups.vista.preguntas.CuestionarioView;
import ec.edu.ups.vista.usuario.*;
import ec.edu.ups.vista.MenuPrincipalView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;


public class UsuarioController {
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    private LoginView loginView;
    private RegistrarseView registrarseView;
    private MenuPrincipalView menuPrincipalView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioListarView usuarioListarView;
    private UsuarioModificarView usuarioModificarView;
    private MensajeInternacionalizacionHandler mensajeI;
    private CuestionarioView cuestionarioView;
    private CuestionarioRecuView cuestionarioRecuView;
    private RecuperacionController preguntasController;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView,
                             RegistrarseView registrarseView,
                             MensajeInternacionalizacionHandler mensajeI) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuario = null;
        this.mensajeI = mensajeI;
        this.registrarseView = registrarseView;
        configurarEventosEnVistas();
    }
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAOBinario("ruta/a/tu/archivo/usuarios.bin");
    }

    public void setPreguntasDependencias(CuestionarioView cuestionarioView, CuestionarioRecuView cuestionarioRecuView, RecuperacionDAO preguntasDAO, MensajeInternacionalizacionHandler mensajeI, UsuarioController usuarioController) {
        this.cuestionarioView = cuestionarioView;
        this.preguntasController = new RecuperacionController(cuestionarioView, cuestionarioRecuView, preguntasDAO,mensajeI,usuarioController);
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
    public void setUsuarioModificarView(UsuarioModificarView usuarioModificarView) {
        this.usuarioModificarView = usuarioModificarView;
        configurarEventosEnModificar();
    }

    private void configurarEventosEnModificar() {
        usuarioModificarView.getBtnBuscarModi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usuarioModificarView.getTxtUsuarioModi().getText().trim();
                if (!username.isEmpty()) {
                    buscarYMostrarUsuario(username);
                } else {
                    JOptionPane.showMessageDialog(usuarioModificarView, mensajeI.get("mensaje.ingrese.usuario"));
                }
            }
        });

        usuarioModificarView.getBtnModificarU().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarUsuario();
            }
        });
    }

    private void configurarEventosEnVistas(){
        loginView. getBtnBuscarRuta().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ruta();
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

    private void configuracionEventosEnRegistrarse() {
        registrarseView.getBtnGuardarRespuestas().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarRespuestas();
            }
        });
        registrarseView.getBtnRegistrarse2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crear();
            }
        });
    }

    private void configurarEventoOlvidoContrasena() {
        loginView.getBtnOlvidarContra().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cuestionarioView != null) {
                    cuestionarioView.setVisible(true);
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

    public void buscarYMostrarUsuario(String username) {
        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario != null) {
            usuarioModificarView.cargarUsuario(usuario);
        } else {
            JOptionPane.showMessageDialog(usuarioModificarView, mensajeI.get("mensaje.usuario.no.encontrado"));
        }
    }

    private void ruta() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona la carpeta de almacenamiento");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int seleccion = chooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File carpetaSeleccionada = chooser.getSelectedFile();

            if (carpetaSeleccionada != null && carpetaSeleccionada.exists() && carpetaSeleccionada.isDirectory()) {
                loginView.getTxtCampoDeRuta().setText(carpetaSeleccionada.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "La carpeta seleccionada no existe o no es válida.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una carpeta para continuar.");
        }
    }





    private void modificarUsuario() {
        String username = usuarioModificarView.getTxtUsuarioModis().getText().trim();
        String contrasenia = usuarioModificarView.getTxtContraseniaModi().getText().trim();
        Object rolSeleccionado = usuarioModificarView.getCbxRol().getSelectedItem();

        if (username.isEmpty() || contrasenia.isEmpty() || rolSeleccionado == null) {
            JOptionPane.showMessageDialog(usuarioModificarView, mensajeI.get("mensaje.complete.campos"));
            return;
        }

        Usuario usuarioExistente = usuarioDAO.buscarPorUsername(username);
        if (usuarioExistente == null) {
            JOptionPane.showMessageDialog(usuarioModificarView, mensajeI.get("mensaje.usuario.no.encontrado"));
            return;
        }

        try {
            usuarioExistente.setContrasenia(contrasenia);
            usuarioExistente.setRol((Rol) rolSeleccionado);
            usuarioDAO.actualizar(usuarioExistente);
            JOptionPane.showMessageDialog(usuarioModificarView, mensajeI.get("mensaje.usuario.modificado"));
        } catch (UsuarioException ex) {
            JOptionPane.showMessageDialog(usuarioModificarView, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void autenticar(){
        String username = loginView.getTxtUsername().getText();
        String contrasenia = new String(loginView.getTxtContrasenia().getPassword());

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if(usuario == null){
            loginView.mostrarMensaje(mensajeI.get("mensaje.login.error"));
        }else{
            loginView.mostrarMensaje(mensajeI.get("mensaje.bienvenida") + username);
            loginView.dispose();
        }
    }

    private void crear() {
        String usuarioT = registrarseView.getUsuario();
        String cedula = registrarseView.getCedula();
        String contrasenia = registrarseView.getContrasenia();
        String confirmarContrasenia = registrarseView.getConfirmarContrasenia();
        String nombre = registrarseView.getNombreCompleto();
        String fechaNac = registrarseView.getFechaNacimiento();
        String correo = registrarseView.getCorreo();
        String telefono = registrarseView.getTelefono();
        String pregunta1 = registrarseView.getPregunta1();
        String pregunta2 = registrarseView.getPregunta2();
        String pregunta3 = registrarseView.getPregunta3();
        String respuesta1 = registrarseView.getRespuesta1();
        String respuesta2 = registrarseView.getRespuesta2();
        String respuesta3 = registrarseView.getRespuesta3();

        if (usuarioT.isEmpty() || cedula.isEmpty() || contrasenia.isEmpty() || confirmarContrasenia.isEmpty()
                || nombre.isEmpty() || fechaNac.isEmpty() || correo.isEmpty() || telefono.isEmpty()
                || respuesta1.isEmpty() || respuesta2.isEmpty() || respuesta3.isEmpty()) {
            registrarseView.mostrarMensaje(mensajeI.get("mensaje.campos.obligatorios"));
            return;
        }

        if (!contrasenia.equals(confirmarContrasenia)) {
            registrarseView.mostrarMensaje(mensajeI.get("mensaje.contrasenias.no.coinciden"));
            return;
        }

        if (pregunta1.equals(pregunta2) || pregunta1.equals(pregunta3) || pregunta2.equals(pregunta3)) {
            registrarseView.mostrarMensaje(mensajeI.get("mensaje.preguntas.iguales"));
            return;
        }

        if (usuarioDAO.buscarPorUsername(usuarioT) != null) {
            registrarseView.mostrarMensaje(mensajeI.get("mensaje.usuario.existente"));
            registrarseView.limpiarCampos();
            return;
        }

        try {
            Usuario.validarCedula(cedula);

            List<String> preguntas = new ArrayList<>();
            preguntas.add(pregunta1);
            preguntas.add(pregunta2);
            preguntas.add(pregunta3);

            List<String> respuestas = new ArrayList<>();
            respuestas.add(respuesta1);
            respuestas.add(respuesta2);
            respuestas.add(respuesta3);

            Usuario nuevoUsuario = new Usuario(usuarioT, contrasenia, Rol.USUARIO, nombre, fechaNac, correo, telefono);
            nuevoUsuario.setUsername(cedula);
            nuevoUsuario.setPreguntas(preguntas);
            nuevoUsuario.setRespuestas(respuestas);

            nuevoUsuario.setContrasenia(contrasenia);

            usuarioDAO.crear(nuevoUsuario);
            registrarseView.mostrarMensaje(mensajeI.get("mensaje.usuario.registrado"));
            registrarseView.dispose();
        } catch (CedulaValidationException | UsuarioException e) {
            registrarseView.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void buscarUsuarios() {
        String nombre = usuarioListarView.getTxtNombreUsu().getText().trim();
        DefaultTableModel modelo = usuarioListarView.getModelo();
        modelo.setRowCount(0);

        if (nombre.isEmpty()) {
            usuarioListarView.mostrarMensaje(mensajeI.get("mensaje.usuario.buscar.vacio"));
            return;
        }
        Usuario usuario = usuarioDAO.buscarPorUsername(nombre);
        if (usuario != null) {
            modelo.addRow(new Object[]{usuario.getUsername(), usuario.getRol().name()});
        } else {
            usuarioListarView.mostrarMensaje(mensajeI.get("mensaje.usuario.no.encontrado"));
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
            usuarioEliminarView.mostrarMensaje(mensajeI.get("mensaje.usuario.no.encontrado"));
        }
    }

    public void eliminarUsuario(){
        String usernameEliminar = usuarioEliminarView.getTxtUsuario().getText();
        String contraseniaAdmin = usuarioEliminarView.getTxtContraseniaE().getText();

        if (getUsuarioAutenticado() == null || getUsuarioAutenticado().getRol() != Rol.ADMINISTRADOR) {
            usuarioEliminarView.mostrarMensaje(mensajeI.get("mensaje.usuario.no.autorizado"));
            return;
        }
        if (!getUsuarioAutenticado().getContrasenia().equals(contraseniaAdmin)) {
            usuarioEliminarView.mostrarMensaje(mensajeI.get("mensaje.usuario.contrasenia.incorrecta"));
            return;
        }
        Usuario usuarioAEliminar = usuarioDAO.buscarPorUsername(usernameEliminar);
        if (usuarioAEliminar.getUsername().equals(getUsuarioAutenticado().getUsername())) {
            usuarioEliminarView.mostrarMensaje(mensajeI.get("mensaje.usuario.no.puede.eliminarse"));
            return;
        }
        usuarioDAO.eliminar(usernameEliminar);
        usuarioEliminarView.mostrarMensaje(mensajeI.get("mensaje.usuario.eliminado"));
    }
    public void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(
                menuPrincipalView,
                mensajeI.get("mensaje.cerrar.sesion"),
                mensajeI.get("mensaje.cerrar.titulo"),
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            if (menuPrincipalView != null) {
                menuPrincipalView.dispose();
            }
            if (loginView == null) {
                loginView = new LoginView(mensajeI);
            }
            loginView.limpiarCampos();
            loginView.setVisible(true);
            loginView.toFront();
            loginView.requestFocus();

            this.usuario = null;
        }
    }

    public void setMensajeInternacionalizacionHandler(MensajeInternacionalizacionHandler mensajeI) {
        this.mensajeI = mensajeI;
    }

    private void guardarRespuestas() {
        String pregunta1 = (String) registrarseView.getCbxP1().getSelectedItem();
        String pregunta2 = (String) registrarseView.getCbxP2().getSelectedItem();
        String pregunta3 = (String) registrarseView.getCbxPre3().getSelectedItem();

        String respuesta1 = registrarseView.getTxtResp1().getText().trim();
        String respuesta2 = registrarseView.getTxtResp2().getText().trim();
        String respuesta3 = registrarseView.getTxtResp3().getText().trim();

        if (respuesta1.isEmpty() || respuesta2.isEmpty() || respuesta3.isEmpty()) {
            JOptionPane.showMessageDialog(registrarseView, "Por favor, complete todas las respuestas.");
            return;
        }

        if (pregunta1.equals(pregunta2) || pregunta1.equals(pregunta3) || pregunta2.equals(pregunta3)) {
            JOptionPane.showMessageDialog(registrarseView, "Las preguntas de seguridad deben ser diferentes.");
            return;
        }

        JOptionPane.showMessageDialog(registrarseView, "Respuestas guardadas correctamente.");
        registrarseView.getBtnRegistrarse2().setEnabled(true);
    }


    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
}
