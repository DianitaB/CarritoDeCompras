package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                PrincipalView principalView = new PrincipalView();

                //instanciamos DAO (Singleton)
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                CarritoDAO carritoDAO = new CarritoDAOMemoria();

                //instancio vistas
                ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoModificarView productoModificarView = new ProductoModificarView();
                ProductoEliminarView productoEliminarView = new ProductoEliminarView();
                CarritoAnadirView carritoAnadirView = new CarritoAnadirView();

                //instanciamos Controladores
                ProductoController productoController = new ProductoController(
                        productoAnadirView,
                        productoListaView,
                        productoModificarView,
                        productoEliminarView,
                        carritoAnadirView,
                        productoDAO
                );
                CarritoController carritoController = new CarritoController(carritoDAO, productoDAO, carritoAnadirView);

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
                principalView.getMenuItemActualizarProducto().addActionListener(new ActionListener() {
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
            }
        });
    }
}