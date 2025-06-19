package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private ProductoAnadirView productoAnadirView;
    private ProductoListaView productoListaView;
    private ProductoModificarView productoModificarView;
    private ProductoEliminarView productoEliminarView;
    private CarritoAnadirView carritoAnadirView;
    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public ProductoAnadirView getProductoAnadirView() {
        return productoAnadirView;
    }

    public ProductoListaView getProductoListaView() {
        return productoListaView;
    }

    public void setProductoAnadirView(ProductoAnadirView productoAnadirView) {
        this.productoAnadirView = productoAnadirView;
        this.configurarAnadirEventos();
    }

    public void setProductoListaView(ProductoListaView productoListaView) {
        this.productoListaView = productoListaView;
        this.configurarListaEventos();
    }

    public void setProductoModificarView(ProductoModificarView productoModificarView) {
        this.productoModificarView = productoModificarView;
        this.configurarModificarEventos();
    }

    public void setProductoEliminarView(ProductoEliminarView productoEliminarView) {
        this.productoEliminarView = productoEliminarView;
        this.configurarEliminarEventos();
    }

    public void setCarritoAnadirView(CarritoAnadirView carritoAnadirView) {
        this.carritoAnadirView = carritoAnadirView;
        this.configurarCarritoAnadirEventos();
    }

    private void configurarCarritoAnadirEventos() {
        carritoAnadirView.getBtnAñadir().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               añadirProducto();
           }
        });
        carritoAnadirView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        carritoAnadirView.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoAnadirView.limpiarCampos();
            }
        });
        carritoAnadirView.getBtnBuscar2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigo();
            }
        });
    }
    private void configurarEliminarEventos() {
        productoEliminarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigo();
            }
        });
        productoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        productoEliminarView,
                        "¿Está seguro de que desea eliminar este producto?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
                    productoDAO.eliminar(codigo);
                    productoEliminarView.mostrarMensaje("Producto eliminado correctamente");
                    productoEliminarView.limpiarCampos();
                } else {
                    productoEliminarView.mostrarMensaje("Eliminación cancelada");
                }
            }
        });

    }
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
                buscarPorCodigo();
            }
        });
    }

    private void configurarAnadirEventos() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });
    }

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
    private void buscarPorCodigo() {
        int codigo;
        Producto producto;

        if (productoModificarView != null && productoModificarView.isVisible()) {
            codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
            producto = productoDAO.buscarPorCodigo(codigo);

            if (producto != null) {
                productoModificarView.getTxtNombre().setText(producto.getNombre());
                productoModificarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
            } else {
                productoModificarView.mostrarMensaje("Producto no encontrado");
                productoModificarView.limpiarCampos();
            }

        } else if (productoEliminarView != null && productoEliminarView.isVisible()) {
            codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
            producto = productoDAO.buscarPorCodigo(codigo);

            if (producto != null) {
                productoEliminarView.getTxtNombre().setText(producto.getNombre());
                productoEliminarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
            } else {
                productoEliminarView.mostrarMensaje("Producto no encontrado");
                productoEliminarView.limpiarCampos();
            }
        } else if (carritoAnadirView != null && carritoAnadirView.isVisible()) {
            codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
            producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                carritoAnadirView.getTxtNombre().setText(producto.getNombre());
                carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
            } else {
                carritoAnadirView.mostrarMensaje("Producto no encontrado");
                carritoAnadirView.limpiarCampos();
            }
        }
    }
    private void añadirProducto() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());

        Producto p = productoDAO.buscarPorCodigo(codigo);
        if (p != null) {
            double total = p.getPrecio() * cantidad;
            DefaultTableModel m = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
            m.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), cantidad,total});
            carritoAnadirView.limpiarCampos();
            calcularTotales();
        }
    }
    private void calcularTotales() {
        DefaultTableModel m = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        double sub = 0;
        for (int i = 0; i < m.getRowCount(); i++)
            sub += (double) m.getValueAt(i, 4);

        double iva = sub * 0.15, total = sub + iva;
        carritoAnadirView.getTxtSubTotal().setText(String.format("%.2f", sub));
        carritoAnadirView.getTxtIva().setText(String.format("%.2f", iva));
        carritoAnadirView.getTxtTotal().setText(String.format("%.2f", total));

    }
    private void modificarProducto() {
        int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
        String nombre = productoModificarView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoModificarView.getTxtPrecio().getText());

        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.actualizar(producto);
        productoModificarView.mostrarMensaje("Producto modificado correctamente");
        productoModificarView.limpiarCampos();
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
}