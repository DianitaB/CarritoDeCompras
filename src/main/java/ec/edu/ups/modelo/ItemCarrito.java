package ec.edu.ups.modelo;

/**
 * Representa un ítem dentro del carrito de compras,
 * compuesto por un producto y una cantidad específica.
 */
public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    /**
     * Constructor sin parámetros.
     */
    public ItemCarrito() {
    }

    /**
     * Constructor que inicializa el ítem con un producto y una cantidad.
     *
     * @param producto Producto asociado al ítem.
     * @param cantidad Cantidad del producto (debe ser mayor que cero).
     * @throws IllegalArgumentException si el producto es nulo o la cantidad es menor o igual a cero.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        setProducto(producto);
        setCantidad(cantidad);
    }

    /**
     * Establece el producto de este ítem.
     *
     * @param producto Producto a asignar (no puede ser nulo).
     * @throws IllegalArgumentException si el producto es nulo.
     */
    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        this.producto = producto;
    }

    /**
     * Establece la cantidad de productos en este ítem.
     * @param cantidad Cantidad a asignar (debe ser mayor que cero).
     * @throws IllegalArgumentException si la cantidad es menor o igual a cero.
     */
    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto asociado a este ítem.
     * @return Producto asignado.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad de productos en este ítem.
     * @return Cantidad asignada.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el subtotal de este ítem, multiplicando el precio del producto por la cantidad.
     * @return Subtotal calculado.
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    /**
     * Representación en texto del ítem, mostrando producto, cantidad y subtotal.
     * @return Cadena con información del ítem.
     */
    @Override
    public String toString() {
        return producto.toString() + " x " + cantidad + " = $" + getSubtotal();
    }
}
