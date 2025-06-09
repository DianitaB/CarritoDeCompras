package ec.edu.ups.poo.servicio;

import ec.edu.ups.poo.model.Item;
import ec.edu.ups.poo.model.Producto;

import java.util.Iterator;
import java.util.List;

public class CarritoServicioImpl implements CarritoService {
    private final List<Item> items;

    public void eliminarProducto(int codigoProducto) {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getProducto().getCodigo() == codigoProducto) {
                //
            }
        }

    }
    public double calcularTotal(){
        double total = 0;
        for (Item item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }

    public List<Item> obtenerItems() {
        return items;
    }
    publi
}
