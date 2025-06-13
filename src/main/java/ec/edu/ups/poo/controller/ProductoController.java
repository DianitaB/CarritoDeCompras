package ec.edu.ups.poo.controller;

import ec.edu.ups.poo.dao.ProductoDAO;
import ec.edu.ups.poo.model.Producto;
import ec.edu.ups.poo.view.ProductoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoController {

    private final ProductoView productoView;
    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO, ProductoView productoView) {
        this.productoDAO = productoDAO;
        this.productoView = productoView;
        configurarEventos();
    }

    private void configurarEventos() {
        productoView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoView.getTxtId().getText());
        String nombre = productoView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoView.mostrarMensaje("Producto guardado correctamente");
        productoView.limpiarCampos();
        productoView.mostrarProductos(productoDAO.listarTodos());
    }
}