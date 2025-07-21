package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación que utiliza un archivo binario para productos.
 */
public class ProductoDAOBinario implements ProductoDAO {

    private final File archivo;

    public ProductoDAOBinario(String rutaCarpeta) {
        this.archivo = new File(rutaCarpeta, "productos.dat");

        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                guardarLista(new ArrayList<>());
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear archivo producto", e);
            }
        }
    }

    /**
     * Carga la lista de productos desde el archivo binario.
     * @return Lista de productos
     */
    private List<Producto> cargarLista() {
        if (archivo.length() == 0) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Producto>) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar productos", e);
        }
    }

    /**
     * Guarda lalista de productos en el archivo binario.
     * @param lista Lista de productos a guardar
     */
    private void guardarLista(List<Producto> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar productos", e);
        }
    }

    /**
     * Crea un nuevo prodcuto y lo agrega a la lista.
     * @param producto Objeto Producto a registar
     */
    @Override
    public void crear(Producto producto) {
        List<Producto> lista = cargarLista();
        lista.add(producto);
        guardarLista(lista);
    }

    /**
     * Busca un producto por su código único.
     * @param codigo Código del producto
     * @return Producto encontrado o null si no existe.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        return cargarLista().stream().filter(p -> p.getCodigo() == codigo).findFirst().orElse(null);
    }

    /**
     * Busca un producto cuyo nombre comience con el texto proporcionado.
     * @param nombre Nombre del producto o parte del nombre
     * @return Lista de productos coincidentes
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : cargarLista()) {
            if (p.getNombre().startsWith(nombre)) lista.add(p);
        }
        return lista;
    }

    /**
     * Actualiza un producto existente en la lista.
     * @param producto Producto con información actualizada
     */
    @Override
    public void actualizar(Producto producto) {
        List<Producto> lista = cargarLista();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == producto.getCodigo()) {
                lista.set(i, producto);
                guardarLista(lista);
                return;
            }
        }
    }

    /**
     * Elimina un producto por su código
     * @param codigo Código del producto a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        List<Producto> lista = cargarLista();
        lista.removeIf(p -> p.getCodigo() == codigo);
        guardarLista(lista);
    }

    /**
     * Modifica un producto existente.
     * @param producto Producto con datos modificados
     * @return
     */
    @Override
    public boolean modificar(Producto producto) {
        actualizar(producto);
        return true;
    }

    /**
     * Lista de todos los productos disponibles
     * @return Lista completa de productos
     */
    @Override
    public List<Producto> listarTodos() {
        return cargarLista();
    }
}