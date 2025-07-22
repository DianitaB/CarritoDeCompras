package ec.edu.ups.modelo;

/**
 * Representa un producto con código único, nombre y precio.
 */
public class Producto {
    private int codigo;
    private String nombre;
    private double precio;

    /**
     * Constructor sin parámetros.
     */
    public Producto() {
    }

    /**
     * Constructor que inicializa un producto con código, nombre y precio.
     *
     * @param codigo Código único del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     */
    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Establece el código del producto.
     * @param codigo Nuevo código.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto.
     * @param precio Nuevo precio.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el código del producto.
     * @return Código actual.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre actual.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     * @return Precio actual.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Representación en texto del producto mostrando nombre y precio.
     * @return Cadena con nombre y precio.
     */
    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }

}
