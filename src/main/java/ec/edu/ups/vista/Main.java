package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.*;
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
import java.io.File;

public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        UsuarioDAO usuarioDAO = new UsuarioDAOMemoria().getInstancia();
        ProductoDAO productoDAO = new ProductoDAOMemoria();
        CarritoDAO carritoDAO = new CarritoDAOMemoria();
        RecuperacionDAO preguntasDAO = new RecuperacionDAOMemoria();

        MensajeInternacionalizacionHandler mensajeI = new MensajeInternacionalizacionHandler("es", "EC");

        LoginView loginView = new LoginView(mensajeI);
        loginView.setVisible(true);
        RegistrarseView registrarseView = new RegistrarseView(mensajeI);
        CuestionarioView cuestionarioView = new CuestionarioView(mensajeI);
        CuestionarioRecuView cuestionarioRecuView = new CuestionarioRecuView(mensajeI);

        UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView, registrarseView, mensajeI);
        usuarioController.setPreguntasDependencias(cuestionarioView, cuestionarioRecuView, preguntasDAO, mensajeI, usuarioController);

        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = loginView.getTipoAlmacenamiento();
                String ruta = loginView.getRutaArchivos();

                File carpeta = new File(ruta);
                if (!carpeta.exists()) {
                    carpeta.mkdirs();
                }
                UsuarioDAO usuarioDAO_local;
                ProductoDAO productoDAO_local;
                CarritoDAO carritoDAO_local;
                RecuperacionDAO recuperacionDAO_local;

                switch (tipo) {
                    case "Memoria":
                        usuarioDAO_local = new UsuarioDAOMemoria();
                        productoDAO_local = new ProductoDAOMemoria();
                        carritoDAO_local = new CarritoDAOMemoria();
                        recuperacionDAO_local = new RecuperacionDAOMemoria();
                        break;
                    case "Archivo de texto":
                        usuarioDAO_local = new UsuarioDAOTexto(new File(ruta, "usuarios.txt").getAbsolutePath());
                        productoDAO_local = new ProductoDAOTexto(new File(ruta, "productos.txt").getAbsolutePath());
                        carritoDAO_local = new CarritoDAOTexto(ruta, usuarioDAO_local, productoDAO_local);
                        recuperacionDAO_local = new RecuperacionDAOTexto(new File(ruta, "recuperacion.txt").getAbsolutePath());
                        break;
                    case "Archivo Binario":
                        usuarioDAO_local = new UsuarioDAOBinario(ruta);
                        productoDAO_local = new ProductoDAOBinario(ruta);
                        carritoDAO_local = new CarritoDAOBinario(ruta, usuarioDAO_local, productoDAO_local);
                        recuperacionDAO_local = new RecuperacionDAOBinario(ruta);
                        break;
                    default:
                        JOptionPane.showMessageDialog(loginView, "Tipo de almacenamiento inválido");
                        return;
                }

                UsuarioController usuarioController = new UsuarioController(usuarioDAO_local, loginView, registrarseView, mensajeI);

                usuarioController.setPreguntasDependencias(cuestionarioView, cuestionarioRecuView, recuperacionDAO_local, mensajeI, usuarioController);

                usuarioController.autenticar();

                Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();

                if (usuarioAutenticado != null) {
                    loginView.dispose();

                    MenuPrincipalView principalView = new MenuPrincipalView(mensajeI);

                    // Crear vistas
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

                    // Crear controladores con DAOs actualizados
                    ProductoController productoController = new ProductoController(
                            productoAnadirView,
                            productoListaView,
                            productoModificarView,
                            productoEliminarView,
                            carritoAnadirView,
                            productoDAO_local,
                            mensajeI
                    );

                    CarritoController carritoController = new CarritoController(
                            carritoDAO_local,
                            productoDAO_local,
                            carritoAnadirView,
                            carritoListarView,
                            carritoEliminarView,
                            carritoModificarView,
                            carritoDetalleView,
                            mensajeI,
                            usuarioAutenticado
                    );

                    // Configurar controlador usuario con vistas y controlador principal
                    usuarioController.setMenuPrincipalView(principalView);
                    usuarioController.setUsuarioListarView(usuarioListarView);
                    usuarioController.setUsuarioEliminarView(usuarioEliminarView);
                    usuarioController.setUsuarioModificarView(usuarioModificarView);
                    usuarioController.setMensajeInternacionalizacionHandler(mensajeI);

                    productoController.setProductoAnadirView(productoAnadirView);
                    productoController.setProductoListaView(productoListaView);
                    productoController.setProductoModificarView(productoModificarView);
                    productoController.setProductoEliminarView(productoEliminarView);

                    principalView.setVisible(true);
                    principalView.mostrarUsuario(usuarioAutenticado.getUsername());

                    principalView.configurarOpcionesPorRolUsuario(usuarioAutenticado.getRol().name());

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

                    // Cambiar idiomas
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

                } else {
                    JOptionPane.showMessageDialog(loginView, "Usuario o contraseña incorrectos");
                }
            }
        });
    }
}
