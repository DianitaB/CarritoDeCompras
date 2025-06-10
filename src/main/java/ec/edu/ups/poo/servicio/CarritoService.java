package ec.edu.ups.poo.servicio;

import ec.edu.ups.poo.model.ItemCarrito;
import ec.edu.ups.poo.model.Producto;

import java.util.List;

public interface CarritoService {

    void agregarProducto(Producto producto, int cantidad);

    void eliminarProducto(int codigoProducto);

    void vaciarCarrito();

    double calcularTotal();

    List<ItemCarrito> obtenerItems();

    boolean estaVacio();

}