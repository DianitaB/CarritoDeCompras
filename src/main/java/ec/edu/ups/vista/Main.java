package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PrincipalView principalView = new PrincipalView();
                ProductoDAO productoDAO = new ProductoDAOMemoria();
                //instancio vistas
                ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ProductoModificarView productoModificarView = new ProductoModificarView();
                ProductoEliminarView productoEliminarView = new ProductoEliminarView();
                CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
                ProductoController productoController = new ProductoController(productoDAO);

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