package ec.edu.ups.poo.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Item> items = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new Item(producto, cantidad));
    }
    public double calularTotal(){
        double total = 0;
        for(Item item: items){
            total += item.calcularSubTotal();
        }
        return total;
    }



}
