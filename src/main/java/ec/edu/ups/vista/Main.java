package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.PreguntasController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.PreguntasDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
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

                //Iniciar Sesion
                LoginView loginView = new LoginView(mensajeI);
                RegistrarseView registrarseView = new RegistrarseView(mensajeI);
                PreguntasDAO preguntasDAO = new PreguntasDAOMemoria();
                CuestionarioView cuestionarioView = new CuestionarioView();
                CuestionarioRecuView cuestionarioRecuView = new CuestionarioRecuView();

                UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView, registrarseView);
                loginView.setVisible(true);
                usuarioController.setPreguntasDependencias(cuestionarioView, cuestionarioRecuView, preguntasDAO, mensajeI,usuarioController);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                        if (usuarioAutenticado != null) {

                            //instanciamos DAO (Singleton)
                            ProductoDAO productoDAO = new ProductoDAOMemoria();
                            CarritoDAO carritoDAO = new CarritoDAOMemoria();
                            PreguntasDAO preguntasDAO = new PreguntasDAOMemoria();
                            MenuPrincipalView principalView = new MenuPrincipalView(mensajeI);


                            //instancio vistas
                            ProductoAnadirView productoAnadirView = new ProductoAnadirView(mensajeI);
                            ProductoListaView productoListaView = new ProductoListaView();
                            ProductoModificarView productoModificarView = new ProductoModificarView();
                            ProductoEliminarView productoEliminarView = new ProductoEliminarView();

                            CarritoAnadirView carritoAnadirView = new CarritoAnadirView(mensajeI);
                            CarritoListarView carritoListarView = new CarritoListarView(mensajeI);
                            CarritoEliminarView carritoEliminarView = new CarritoEliminarView(mensajeI);
                            CarritoModificarView carritoModificarView = new CarritoModificarView(mensajeI);
                            CarritoDetalleView carritoDetalleView = new CarritoDetalleView(mensajeI);

                            UsuarioListarView usuarioListarView = new UsuarioListarView(mensajeI);
                            UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView(mensajeI);
                            UsuarioModificarView usuarioModificarView = new UsuarioModificarView(mensajeI);



                            //instanciamos Controladores
                            ProductoController productoController = new ProductoController(
                                    productoAnadirView,
                                    productoListaView,
                                    productoModificarView,
                                    productoEliminarView,
                                    carritoAnadirView,
                                    productoDAO
                            );
                            CarritoController carritoController = new CarritoController(
                                    carritoDAO,
                                    productoDAO,
                                    carritoAnadirView,
                                    carritoListarView,
                                    carritoEliminarView,
                                    carritoModificarView,
                                    carritoDetalleView,
                                    mensajeI);

                            // añadir producto
                            productoController.setProductoAnadirView(productoAnadirView);
                            //listar producto
                            productoController.setProductoListaView(productoListaView);
                            // modificar producto
                            productoController.setProductoModificarView(productoModificarView);
                            // eliminar producto
                            productoController.setProductoEliminarView(productoEliminarView);
                            // carrito
                            productoController.setCarritoAnadirView(carritoAnadirView);
                            // usuario
                            usuarioController.setUsuarioListarView(usuarioListarView);
                            usuarioController.setMenuPrincipalView(principalView);
                            usuarioController.setUsuarioEliminarView(usuarioEliminarView);


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
                            principalView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    usuarioController.cerrarSesion();
                                }
                            });

                        }
                    }
                });
            }
        });
    }
}