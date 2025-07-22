package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación que almacena productos en archivos de texto
 */
public class ProductoDAOTexto implements ProductoDAO {

    private final File archivo;

    public ProductoDAOTexto(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try {
            if (!archivo.exists()) archivo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Error creando archivo de texto de producto", e);
        }
    }

    /**
     * Lee todos los productos almacenados en el archivo de texto.
     * @return Lista de productos leídos desde el archivo
     */
    private List<Producto> leerTodos() {
        List<Producto> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    lista.add(new Producto(codigo, nombre, precio));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo productos del archivo", e);
        }
        return lista;
    }

    /**
     * Escribe la lista completa de productos en el archivo de texto, sobrescribiéndolo.
     * @param lista
     */
    private void escribirTodos(List<Producto> lista) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Producto p : lista) {
                writer.write(p.getCodigo() + "," + p.getNombre() + "," + p.getPrecio());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error escribiendo productos en archivo", e);
        }
    }

    /**
     * Crea un nuevo producto y lo guarda en el archivo.
     * @param producto Producto a registar
     */
    @Override
    public void crear(Producto producto) {
        List<Producto> lista = leerTodos();
        lista.add(producto);
        escribirTodos(lista);
    }

    /**
     * Busca un producto por su código único.
     * @param codigo Código del producto
     * @return Producto encontrado o null si no existe
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        return leerTodos().stream().filter(p -> p.getCodigo() == codigo).findFirst().orElse(null);
    }

    /**
     * Busca productos cuyo nombre comience con la cadena proporcionada.
     * @param nombre Nombre del producto o parte del nombre
     * @return Lista de productos coincidentes
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : leerTodos()) {
            if (p.getNombre().startsWith(nombre)) lista.add(p);
        }
        return lista;
    }

    /**
     * Actualiza un producto existente en la lista y guarda cambios en el archivo.
     * @param producto Producto con información actualizada
     */
    @Override
    public void actualizar(Producto producto) {
        List<Producto> lista = leerTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == producto.getCodigo()) {
                lista.set(i, producto);
                escribirTodos(lista);
                return;
            }
        }
    }

    /**
     * Elimina un prooducto por su código.
     * @param codigo Código del producto a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        List<Producto> lista = leerTodos();
        lista.removeIf(p -> p.getCodigo() == codigo);
        escribirTodos(lista);
    }

    /**
     * Modifica un producto existente
     * @param producto Producto con datos modificados
     * @return true si se actualizó correctamente
     */
    @Override
    public boolean modificar(Producto producto) {
        actualizar(producto);
        return true;
    }

    /**
     * Lista todos los productos almacenados.
     * @return
     */
    @Override
    public List<Producto> listarTodos() {
        return leerTodos();
    }
}