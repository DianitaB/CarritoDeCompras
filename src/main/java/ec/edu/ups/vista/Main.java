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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// DAO, CONTROLLER, VISTAS - se instancian una sola vez
public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        UsuarioDAO usuarioDAO = UsuarioDAOMemoria.getInstancia();
        MensajeInternacionalizacionHandler mensajeI = new MensajeInternacionalizacionHandler("es","EC");


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                LoginView loginView = new LoginView(mensajeI);
                RegistrarseView registrarseView = new RegistrarseView(mensajeI);
                RecuperacionDAO preguntasDAO = new RecuperacionDAOMemoria();
                CuestionarioView cuestionarioView = new CuestionarioView(mensajeI);
                CuestionarioRecuView cuestionarioRecuView = new CuestionarioRecuView(mensajeI);
                UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView, registrarseView,mensajeI);
                loginView.setVisible(true);
                usuarioController.setPreguntasDependencias(cuestionarioView, cuestionarioRecuView, preguntasDAO, mensajeI,usuarioController);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                        if (usuarioAutenticado != null) {
                            ProductoDAO productoDAO = new ProductoDAOMemoria();
                            CarritoDAO carritoDAO = new CarritoDAOMemoria();
                            RecuperacionDAO preguntasDAO = new RecuperacionDAOMemoria();
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
                            usuarioController.setMenuPrincipalView(principalView);
                            principalView.setVisible(true);

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
                                    mensajeI, usuarioAutenticado
                                    );

                            productoController.setProductoAnadirView(productoAnadirView);
                            productoController.setProductoListaView(productoListaView);
                            productoController.setProductoModificarView(productoModificarView);
                            productoController.setProductoEliminarView(productoEliminarView);
                            productoController.setCarritoAnadirView(carritoAnadirView);
                            usuarioController.setUsuarioListarView(usuarioListarView);
                            usuarioController.setUsuarioEliminarView(usuarioEliminarView);
                            usuarioController.setMensajeInternacionalizacionHandler(mensajeI);
                            usuarioController.setUsuarioModificarView(usuarioModificarView);
                            principalView.configurarOpcionesPorRolUsuario(usuarioAutenticado.getRol().name());
                            principalView.mostrarUsuario(usuarioAutenticado.getUsername());

                            principalView.getMenuItemModificarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!usuarioModificarView.isVisible()){
                                        principalView.getjDesktopPane().add(usuarioModificarView);
                                        usuarioModificarView.setVisible(true);
                                    }
                                }
                            });

                            principalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!usuarioEliminarView.isVisible()) {
                                        principalView.getjDesktopPane().add(usuarioEliminarView);
                                        usuarioEliminarView.setVisible(true);
                                    }
                                }
                            });

                            principalView.getMenuItemBuscarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioListarView.isVisible()) {
                                        principalView.getjDesktopPane().add(usuarioListarView);
                                        usuarioListarView.setVisible(true);
                                    }
                                }
                            });

                            principalView.getMenuItemBuscarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!carritoListarView.isVisible()){
                                        principalView.getjDesktopPane().add(carritoListarView);
                                        carritoListarView.setVisible(true);
                                    }
                                }
                            });

                            principalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!carritoEliminarView.isVisible()){
                                        principalView.getjDesktopPane().add(carritoEliminarView);
                                        carritoEliminarView.setVisible(true);
                                    }
                                }
                            });

                            principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoAnadirView.isVisible()) {
                                        principalView.getjDesktopPane().add(productoAnadirView);
                                        productoAnadirView.setVisible(true);
                                    }
                                }
                            });

                            principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoListaView.isVisible()) {
                                        principalView.getjDesktopPane().add(productoListaView);
                                        productoListaView.setVisible(true);
                                    }
                                }
                            });
                            principalView.getMenuItemModificarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoModificarView.isVisible()) {
                                        principalView.getjDesktopPane().add(productoModificarView);
                                        productoModificarView.setVisible(true);
                                    }
                                }

                            });
                            principalView.getMenuItemAñadirCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!carritoAnadirView.isVisible()){
                                        carritoAnadirView.setVisible(true);
                                        principalView.getjDesktopPane().add(carritoAnadirView);
                                    }
                                }
                            });
                            principalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoEliminarView.isVisible()) {
                                        productoEliminarView.setVisible(true);
                                        principalView.getjDesktopPane().add(productoEliminarView);
                                    }
                                }
                            });

                            principalView.getMenuItemIdiomaEspanol().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("es", "EC");
                                }
                            });

                            principalView.getMenuItemIdiomaIngles().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("en", "US");
                                }
                            });
                            principalView.getMenuItemIdiomaFrances().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("fr", "FR");
                                }
                            });
                            principalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    usuarioController.eliminarUsuario();
                                }
                            });
                            principalView.getMenuItemBuscarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    usuarioController.buscarUsuario();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}