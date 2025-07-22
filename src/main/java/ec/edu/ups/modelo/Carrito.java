package ec.edu.ups.modelo;

import java.util.*;

/**
 * Representa un carrito de compras que contiene una lista de productos
 * con sus cantidades, asociado a un usuario.
 * Permite agregar, eliminar productos y calcular valores como subtotal, IVA y total.
 */
public class Carrito {

    /** Porcentaje de IVA aplicado sobre el subtotal (12%). */
    private final double IVA = 0.12;
    /** Contador estático para generar códigos únicos para cada carrito. */
    private static int contador = 1;
    /** Código único del carrito. */
    private int codigo;
    /** Fecha de creación del carrito. */
    private Date fechaCreacion;
    /** Lista de items (productos con cantidad) dentro del carrito. */
    private List<ItemCarrito> items;
    /** Usuario propietario del carrito. */
    private Usuario usuario;

    public Carrito(Usuario usuario) {
        this.usuario = usuario;
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new Date();
    }
    public Carrito() {
        this.usuario = null;
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new Date();
    }


    /**
     * Obtiene el usuario propietario del carrito.
     * @return Usuario asociado.
     */
    public Usuario getUsuario() {return usuario;}

    /**
     * Establece el usuario propietario del carrito.
     * @param usuario Usuario a asignar.
     */
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    /**
     * Obtiene el código único del carrito.
     * @return Código del carrito.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece el código único del carrito.
     * @param codigo Nuevo código para el carrito.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene la fecha de creación del carrito.
     * @return Fecha de creación.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del carrito.
     * @param fechaCreacion Nueva fecha de creación.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Agrega un producto al carrito con la cantidad especificada.
     * @param producto Producto a agregar.
     * @param cantidad Cantidad del producto.
     */
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }


    /**
     * Elimina un producto del carrito según su código.
     * @param codigoProducto Código del producto a eliminar.
     */
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Vacía completamente el carrito, eliminando todos los productos.
     */
    public void vaciarCarrito() {
        items.clear();
    }


    /**
     * Obtiene la lista de items que contiene el carrito.
     * @return Lista de items.
     */
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    /**
     * Calcula el subtotal del carrito sumando el precio por cantidad de cada producto.
     * @return Subtotal sin incluir impuestos.
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }


    /**
     * Calcula el IVA aplicado al subtotal.
     * @return Valor del IVA.
     */
    public double calcularIVA() {
        double subtotal = calcularSubtotal();
        return subtotal * IVA;
    }

    /**
     * Calcula el total del carrito sumando subtotal e IVA.
     * @return Total a pagar.
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    /**
     * Representación en texto del carrito con código, fecha, cantidad de items,
     * subtotal, IVA y total.
     * @return Cadena con información del carrito.
     */
    @Override
    public String toString() {
        return String.format("Carrito #%d | Fecha: %s | Items: %d | Subtotal: $%.2f | IVA: $%.2f | Total: $%.2f",
                codigo,
                fechaCreacion.toString(),
                items.size(),
                calcularSubtotal(),
                calcularIVA(),
                calcularTotal());
    }
}
