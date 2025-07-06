package ec.edu.ups.modelo;

import java.util.*;

public class Carrito {

    private final double IVA = 0.12;

    private static int contador = 1;

    private int codigo;

    private Date fechaCreacion;

    private List<ItemCarrito> items;

    private Usuario usuario;

    public Carrito(Usuario usuario) {
        this.usuario = usuario;
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new Date();
    }

    public Usuario getUsuario() {return usuario;}

    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    public double calcularIVA() {
        double subtotal = calcularSubtotal();
        return subtotal * IVA;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

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
