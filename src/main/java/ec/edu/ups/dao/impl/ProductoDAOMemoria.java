package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria del DAO para Producto.
 */
public class ProductoDAOMemoria implements ProductoDAO {

    private List<Producto> productos;

    /**
     * Constructor que inicializa la lista de productos.
     */
    public ProductoDAOMemoria() {
        productos = new ArrayList<Producto>();
    }

    /**
     * Agrega un nuevo producto a la lista.
     * @param producto a agregar
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }

    /**
     * Busca un producto por su código.
     * @param codigo del producto a buscar
     * @return el producto o null si no existe
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Busca productos por nombre.
     * @param nombre parte inicial del nombre del producto
     * @return lista de productos que coinciden
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().startsWith(nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    /**
     * Actualiza un producto. Lo busco por código y lo reemplazo en la lista.
     * @param producto el producto con la nueva información.
     */
    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
    }

    /**
     * Eliminar un producto de la lista por su código.
     * @param codigo el código del producto a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    /**
     * Metodo modificado
     * @param producto
     * @return
     */
    @Override
    public boolean modificar(Producto producto) {
        return false;
    }

    /**
     * Devuelvo todos los productos en memoria.
     * @return lista de los productos
     */
    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
}