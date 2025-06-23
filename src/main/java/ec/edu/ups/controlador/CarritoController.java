package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.CarritoEliminarView;
import ec.edu.ups.vista.CarritoListarView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private CarritoListarView carritoListarView;
    private CarritoEliminarView carritoEliminarView;
    private Carrito carrito;

    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carrito = new Carrito();
        configurarEventosEnVistas();
    }

    public void setCarritoListarView(CarritoListarView carritoListarView) {
        this.carritoListarView = carritoListarView;
        configurarEventosEnListar();
    }

    public void setCarritoEliminarView(CarritoEliminarView carritoEliminarView) {
        this.carritoEliminarView = carritoEliminarView;
        configurarEventosEnEliminar();
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
    private void configurarEventosEnEliminar(){
        carritoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elimarCarrito();
            }
        });
        carritoEliminarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigoParaEliminar();
            }
        });
    }

    private void configurarEventosEnListar(){
        carritoListarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigo();
            }
        });
        carritoListarView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCarrito();
            }
        });

    }
    private void elimarCarrito() {
        String codigoTexto = carritoEliminarView.getTxtCodigo().getText();

        if (!codigoTexto.matches("\\d+")) {
            carritoEliminarView.mostrarMensaje("Ingrese un código válido.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);

        if (carrito == null) {
            carritoEliminarView.mostrarMensaje("Carrito no encontrado.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro que desea eliminar el carrito con código " + codigo + "?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            carritoDAO.eliminar(codigo);
            carritoEliminarView.mostrarMensaje("Carrito eliminado correctamente.");
            carritoEliminarView.limpiarCampos();
        } else {
            carritoEliminarView.mostrarMensaje("Eliminación cancelada.");
        }
        carritoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void configurarEventosEnVistas() {
        carritoAnadirView.getBtnAñadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProducto();
            }
        });

        carritoAnadirView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCarrito();
            }
        });
        carritoAnadirView.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }
    private void buscarPorCodigoParaEliminar() {
        String codigoTexto = carritoEliminarView.getTxtCodigo().getText();

        if (!codigoTexto.matches("\\d+")) {
            carritoEliminarView.mostrarMensaje("Ingrese un código válido.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);

        DefaultTableModel modelo = (DefaultTableModel) carritoEliminarView.getTblLProductos().getModel();
        modelo.setRowCount(0);

        if (carrito == null) {
            carritoEliminarView.mostrarMensaje("Carrito no encontrado.");
        } else {
            for (ItemCarrito item : carrito.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getProducto().getPrecio() * item.getCantidad()
                });
            }
        }
    }
    private void buscarPorCodigo() {
        int codigo = Integer.parseInt(carritoListarView.getTxtCodigo().getText());
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);

        if (carrito == null) {
            carritoListarView.mostrarMensaje("Carrito no encontrado");
            DefaultTableModel modelo = (DefaultTableModel) carritoListarView.getTblPCarrito().getModel();
            modelo.setRowCount(0);
        } else {
            DefaultTableModel modelo = (DefaultTableModel) carritoListarView.getTblPCarrito().getModel();
            modelo.setRowCount(0);
            for (ItemCarrito item : carrito.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getProducto().getPrecio() * item.getCantidad()
                });
            }
            carritoListarView.getTxtTotal().setText(String.format("%.2f", carrito.calcularTotal()));
        }
    }
    private void limpiarCampos() {
        carrito.vaciarCarrito();
        carritoAnadirView.mostrarMensaje("Carrito Limpiado");
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);
        carritoAnadirView.limpiarCampos();
        carritoAnadirView.getCbxCantidad().setSelectedIndex(0);
    }
    private void guardarCarrito() {
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje("Carrito creado correctamente");
        System.out.println(carritoDAO.listarTodos());
    }

    private void anadirProducto() {

        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        int cantidad =  Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        carrito.agregarProducto(producto, cantidad);
        cargarProductos();
        mostrarTotales();

    }
    private void cargarProductos(){
        List<ItemCarrito> items = carrito.obtenerItems();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setNumRows(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{ item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio() * item.getCantidad() });
        }
    }
    private void listarCarrito(){

            List<Carrito> carritos = carritoDAO.listarTodos();
            DefaultTableModel modelo = (DefaultTableModel) carritoListarView.getTblPCarrito().getModel();
            modelo.setRowCount(0);

            for (Carrito c : carritos) {
                for (ItemCarrito item : c.obtenerItems()) {
                    modelo.addRow(new Object[]{
                            item.getProducto().getCodigo(),
                            item.getProducto().getNombre(),
                            item.getProducto().getPrecio(),
                            item.getCantidad(),
                            item.getProducto().getPrecio() * item.getCantidad()
                    });

            }
            carritoListarView.getTxtTotal().setText("");
        }
    }

    private void mostrarTotales(){
        String subtotal = String.valueOf(carrito.calcularSubtotal());
        String iva = String.valueOf(carrito.calcularIVA());
        String total = String.valueOf(carrito.calcularTotal());

        carritoAnadirView.getTxtSubTotal().setText(subtotal);
        carritoAnadirView.getTxtIva().setText(iva);
        carritoAnadirView.getTxtTotal().setText(total);
    }
}
