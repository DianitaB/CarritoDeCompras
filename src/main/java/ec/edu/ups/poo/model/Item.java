package ec.edu.ups.poo.model;

public class Item {
    private Producto producto;
    private int cantidad;

    public Item(Producto producto, int cantidad) {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double calcularSubTotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return "Item " +
                "Producto: " + producto +
                "Cantidad: " + cantidad;
    }
}
