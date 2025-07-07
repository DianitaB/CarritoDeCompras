package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.RecuperacionDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.*;
import ec.edu.ups.vista.preguntas.CuestionarioRecuView;
import ec.edu.ups.vista.preguntas.CuestionarioView;
import ec.edu.ups.vista.producto.ProductoAnadirView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;
import ec.edu.ups.vista.usuario.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        UsuarioDAO usuarioDAO = new UsuarioDAOMemoria().getInstancia();
        ProductoDAO productoDAO = new ProductoDAOMemoria();
        CarritoDAO carritoDAO = new CarritoDAOMemoria();
        RecuperacionDAO preguntasDAO = new RecuperacionDAOMemoria();
        MensajeInternacionalizacionHandler mensajeI =
                new MensajeInternacionalizacionHandler("es","EC");

        LoginView loginView = new LoginView(mensajeI);
        RegistrarseView registrarseView = new RegistrarseView(mensajeI);
        CuestionarioView cuestionarioView = new CuestionarioView(mensajeI);
        CuestionarioRecuView cuestionarioRecuView = new CuestionarioRecuView(mensajeI);
        UsuarioController usuarioController = new UsuarioController(usuarioDAO,
                loginView,
                registrarseView,
                mensajeI);

        usuarioController.setPreguntasDependencias(cuestionarioView, cuestionarioRecuView, preguntasDAO, mensajeI, usuarioController);

        loginView.setVisible(true);

        // En lugar de usar windowClosed, agregamos listener al botón de login para detectar autenticación
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioController.autenticar();
                Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                if (usuarioAutenticado != null) {
                    // Usuario autenticado, construimos ventana principal y demás vistas solo una vez
                    MenuPrincipalView principalView = new MenuPrincipalView(mensajeI);

                    ProductoAnadirView productoAnadirView = new ProductoAnadirView(mensajeI);
                    ProductoListaView productoListaView = new ProductoListaView(mensajeI);
                    ProductoModificarView productoModificarView = new ProductoModificarView(mensajeI);
                    ProductoEliminarView productoEliminarView = new ProductoEliminarView(mensajeI);

                    CarritoAnadirView carritoAnadirView = new CarritoAnadirView(mensajeI);
                    CarritoListarView carritoListarView = new CarritoListarView(mensajeI);
                    CarritoEliminarView carritoEliminarView = new CarritoEliminarView(mensajeI);
                    CarritoModificarView carritoModificarView = new CarritoModificarView(mensajeI);
                    CarritoDetalleView carritoDetalleView = new CarritoDetalleView(mensajeI);

                    UsuarioListarView usuarioListarView = new UsuarioListarView(mensajeI);
                    UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView(mensajeI);
                    UsuarioModificarView usuarioModificarView = new UsuarioModificarView(mensajeI);

                    ProductoController productoController = new ProductoController(
                            productoAnadirView,
                            productoListaView,
                            productoModificarView,
                            productoEliminarView,
                            carritoAnadirView,
                            productoDAO,
                            mensajeI
                    );

                    CarritoController carritoController = new CarritoController(
                            carritoDAO,
                            productoDAO,
                            carritoAnadirView,
                            carritoListarView,
                            carritoEliminarView,
                            carritoModificarView,
                            carritoDetalleView,
                            mensajeI,
                            usuarioAutenticado
                    );

                    usuarioController.setMenuPrincipalView(principalView);
                    usuarioController.setUsuarioListarView(usuarioListarView);
                    usuarioController.setUsuarioEliminarView(usuarioEliminarView);
                    usuarioController.setUsuarioModificarView(usuarioModificarView);
                    usuarioController.setMensajeInternacionalizacionHandler(mensajeI);

                    principalView.setVisible(true);
                    loginView.dispose();
                    principalView.mostrarUsuario(usuarioAutenticado.getUsername());
                    principalView.configurarOpcionesPorRolUsuario(usuarioAutenticado.getRol().name());

                    // Menús para mostrar vistas internas (desktop pane)
                    principalView.getMenuItemModificarUsuario().addActionListener(ev -> {
                        if (!usuarioModificarView.isVisible()) {
                            principalView.getjDesktopPane().add(usuarioModificarView);
                            usuarioModificarView.setVisible(true);
                        }
                    });

                    principalView.getMenuItemEliminarUsuario().addActionListener(ev -> {
                        if (!usuarioEliminarView.isVisible()) {
                            principalView.getjDesktopPane().add(usuarioEliminarView);
                            usuarioEliminarView.setVisible(true);
                        }
                    });

                    principalView.getMenuItemBuscarUsuario().addActionListener(ev -> {
                        if (!usuarioListarView.isVisible()) {
                            principalView.getjDesktopPane().add(usuarioListarView);
                            usuarioListarView.setVisible(true);
                        }
                    });

                    principalView.getMenuItemBuscarCarrito().addActionListener(ev -> {
                        if (!carritoListarView.isVisible()) {
                            principalView.getjDesktopPane().add(carritoListarView);
                            carritoListarView.setVisible(true);
                        }
                    });

                    principalView.getMenuItemEliminarCarrito().addActionListener(ev -> {
                        if (!carritoEliminarView.isVisible()) {
                            principalView.getjDesktopPane().add(carritoEliminarView);
                            carritoEliminarView.setVisible(true);
                        }
                    });

                    principalView.getMenuItemCrearProducto().addActionListener(ev -> {
                        if (!productoAnadirView.isVisible()) {
                            principalView.getjDesktopPane().add(productoAnadirView);
                            productoAnadirView.setVisible(true);
                        }
                    });

                    principalView.getMenuItemBuscarProducto().addActionListener(ev -> {
                        if (!productoListaView.isVisible()) {
                            principalView.getjDesktopPane().add(productoListaView);
                            productoListaView.setVisible(true);
                        }
                    });

                    principalView.getMenuItemModificarProducto().addActionListener(ev -> {
                        if (!productoModificarView.isVisible()) {
                            principalView.getjDesktopPane().add(productoModificarView);
                            productoModificarView.setVisible(true);
                        }
                    });

                    principalView.getMenuItemAñadirCarrito().addActionListener(ev -> {
                        if (!carritoAnadirView.isVisible()) {
                            principalView.getjDesktopPane().add(carritoAnadirView);
                            carritoAnadirView.setVisible(true);
                        }
                    });

                    principalView.getMenuItemEliminarProducto().addActionListener(ev -> {
                        if (!productoEliminarView.isVisible()) {
                            principalView.getjDesktopPane().add(productoEliminarView);
                            productoEliminarView.setVisible(true);
                        }
                    });

                    ActionListener idiomaEs = ev -> {
                        principalView.cambiarIdioma("es", "EC");
                        productoAnadirView.cambiarIdioma();
                        productoListaView.cambiarIdioma();
                        productoModificarView.cambiarIdioma();
                        productoEliminarView.cambiarIdioma();

                        carritoAnadirView.cambiarIdioma();
                        carritoListarView.cambiarIdioma();
                        carritoEliminarView.cambiarIdioma();
                        carritoModificarView.cambiarIdioma();
                        carritoDetalleView.cambiarIdioma();

                        usuarioListarView.cambiarIdioma();
                        usuarioEliminarView.cambiarIdioma();
                        usuarioModificarView.cambiarIdioma();
                    };

                    ActionListener idiomaEn = ev -> {
                        principalView.cambiarIdioma("en", "US");
                        productoAnadirView.cambiarIdioma();
                        productoListaView.cambiarIdioma();
                        productoModificarView.cambiarIdioma();
                        productoEliminarView.cambiarIdioma();

                        carritoAnadirView.cambiarIdioma();
                        carritoListarView.cambiarIdioma();
                        carritoEliminarView.cambiarIdioma();
                        carritoModificarView.cambiarIdioma();
                        carritoDetalleView.cambiarIdioma();

                        usuarioListarView.cambiarIdioma();
                        usuarioEliminarView.cambiarIdioma();
                        usuarioModificarView.cambiarIdioma();
                    };

                    ActionListener idiomaFr = ev -> {
                        principalView.cambiarIdioma("fr", "FR");
                        productoAnadirView.cambiarIdioma();
                        productoListaView.cambiarIdioma();
                        productoModificarView.cambiarIdioma();
                        productoEliminarView.cambiarIdioma();

                        carritoAnadirView.cambiarIdioma();
                        carritoListarView.cambiarIdioma();
                        carritoEliminarView.cambiarIdioma();
                        carritoModificarView.cambiarIdioma();
                        carritoDetalleView.cambiarIdioma();

                        usuarioListarView.cambiarIdioma();
                        usuarioEliminarView.cambiarIdioma();
                        usuarioModificarView.cambiarIdioma();
                    };

                    principalView.getMenuItemIdiomaEspanol().addActionListener(idiomaEs);
                    principalView.getMenuItemIdiomaIngles().addActionListener(idiomaEn);
                    principalView.getMenuItemIdiomaFrances().addActionListener(idiomaFr);

                    principalView.getMenuItemEliminarUsuario().addActionListener(ev -> usuarioController.eliminarUsuario());
                    principalView.getMenuItemBuscarUsuario().addActionListener(ev -> usuarioController.buscarUsuario());
                }
            }
        });
    }
}
