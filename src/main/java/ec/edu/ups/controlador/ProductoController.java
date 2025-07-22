package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.CarritoAnadirView;
import ec.edu.ups.vista.producto.ProductoAnadirView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador para gestionar las acciones relacionadas con los productos.
 * Maneja eventos de las vistas de añadir, listar, modificar y eliminar productos.
 */
public class ProductoController {

    private ProductoAnadirView productoAnadirView;
    private ProductoListaView productoListaView;
    private ProductoModificarView productoModificarView;
    private ProductoEliminarView productoEliminarView;
    private CarritoAnadirView carritoAnadirView;
    private MensajeInternacionalizacionHandler mensajeI;
    private final ProductoDAO productoDAO;

    /**
     * Constructor principal del controlador de productos.
     *
     * @param productoAnadirView Vista para añadir productos
     * @param productoListaView Vista para listar productos
     * @param productoModificarView Vista para modificar productos
     * @param productoEliminarView Vista para eliminar productos
     * @param carritoAnadirView Vista de carrito (para compatibilidad)
     * @param productoDAO DAO para acceder a productos
     * @param mensajeI Manejador de mensajes internacionalizados
     */
    public ProductoController(ProductoAnadirView productoAnadirView, ProductoListaView productoListaView, ProductoModificarView productoModificarView, ProductoEliminarView productoEliminarView, CarritoAnadirView carritoAnadirView, ProductoDAO productoDAO,MensajeInternacionalizacionHandler mensajeI) {
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoModificarView = productoModificarView;
        this.productoEliminarView = productoEliminarView;
        this.carritoAnadirView = carritoAnadirView;
        this.productoDAO = productoDAO;
        this.mensajeI = mensajeI;
    }
    /**
     * Establece la vista de añadir producto y configura sus eventos.
     * @param productoAnadirView Vista añadir
     */
    public void setProductoAnadirView(ProductoAnadirView productoAnadirView) {
        this.productoAnadirView = productoAnadirView;
        this.configurarAnadirEventos();
    }

    /**
     * Establece la vista de listar productos y configura sus eventos.
     * @param productoListaView Vista lista
     */
    public void setProductoListaView(ProductoListaView productoListaView) {
        this.productoListaView = productoListaView;
        this.configurarListaEventos();
    }
    /**
     * Establece la vista de modificar productos y configura sus eventos.
     * @param productoModificarView Vista modificar
     */
    public void setProductoModificarView(ProductoModificarView productoModificarView) {
        this.productoModificarView = productoModificarView;
        this.configurarModificarEventos();
    }

    /**
     * Establece la vista de eliminar productos y configura sus eventos.
     * @param productoEliminarView Vista eliminar
     */
    public void setProductoEliminarView(ProductoEliminarView productoEliminarView) {
        this.productoEliminarView = productoEliminarView;
        this.configurarEliminarEventos();
    }

    /**
     * Configura los eventos para la vista de eliminar producto.
     */
    private void configurarEliminarEventos() {
        productoEliminarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoParaEliminar();
            }
        });
        productoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });
    }

    /**
     * Configura los eventos para la vista de modificar producto.
     */
    private void configurarModificarEventos() {
        productoModificarView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProducto();
            }
        });
        productoModificarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoParaModificar();
            }
        });
    }

    /**
     * Configura los eventos para la vista de añadir producto.
     */
    private void configurarAnadirEventos() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

    /**
     * Configura los eventos para la vista de listar productos.
     */
    private void configurarListaEventos() {
        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });
    }

    /**
     * Busca un producto por código y muestra sus datos para eliminar.
     */
    private void buscarProductoParaEliminar() {
        int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto != null) {
            productoEliminarView.getTxtNombre().setText(producto.getNombre());
            productoEliminarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        } else {
            productoEliminarView.mostrarMensaje(mensajeI.get("producto.no.encontrado"));
            productoEliminarView.limpiarCampos();
        }
    }

    /**
     * Elimina un producto después de confirmar con el usuario.
     */
    private void eliminarProducto() {
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                mensajeI.get("producto.confirmar.eliminar"),
                mensajeI.get("producto.confirmar.titulo"),
                JOptionPane.YES_NO_OPTION
        );
        if (respuesta == JOptionPane.YES_OPTION) {
            int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
            productoDAO.eliminar(codigo);
            productoEliminarView.mostrarMensaje(mensajeI.get("producto.eliminado.exito"));
            productoEliminarView.limpiarCampos();
        } else {
            productoEliminarView.mostrarMensaje(mensajeI.get("producto.eliminado.cancelado"));
        }
    }

    /**
     * Busca un producto por código para modificarlo.
     */
    private void buscarProductoParaModificar() {
        int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto != null) {
            productoModificarView.getTxtNombre().setText(producto.getNombre());
            productoModificarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        } else {
            productoModificarView.mostrarMensaje(mensajeI.get("producto.no.encontrado"));
            productoModificarView.limpiarCampos();
        }
    }

    /**
     * Modifica un producto existente con nuevos valores ingresados.
     */
    private void modificarProducto() {
        int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
        String nombre = productoModificarView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoModificarView.getTxtPrecio().getText());

        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.actualizar(producto);
        productoModificarView.mostrarMensaje(mensajeI.get("producto.modificado.exito"));
        productoModificarView.limpiarCampos();
    }

    /**
     * Guarda un nuevo producto ingresado en la vista.
     */
    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje(mensajeI.get("producto.guardado.exito"));
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }


    /**
     * Busca productos por nombre y muestra los resultados.
     */
    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    /**
     * Lista todos los productos y los muestra en la vista.
     */
    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
}
