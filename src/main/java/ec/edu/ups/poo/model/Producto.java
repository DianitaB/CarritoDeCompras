package ec.edu.ups.poo.model;

public class Producto {
    private int codigo;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public Producto(int codigo, double precio, String nombre) {
        this.codigo = codigo;
        this.precio = precio;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void getSubTotal() {

    }

    @Override
    public String toString() {
        return "--Producto--" +
                "\nCódigo: " + codigo +
                "\nNombre: " + nombre +
                "\nPrecio: " + precio ;
    }
}
