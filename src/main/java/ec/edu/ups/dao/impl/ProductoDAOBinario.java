package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOBinario implements ProductoDAO {

    private final File archivo;

    public ProductoDAOBinario(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                guardarLista(new ArrayList<>());
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear archivo producto", e);
            }
        }
    }

    private List<Producto> cargarLista() {
        if (archivo.length() == 0) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Producto>) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar productos", e);
        }
    }

    private void guardarLista(List<Producto> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar productos", e);
        }
    }

    @Override
    public void crear(Producto producto) {
        List<Producto> lista = cargarLista();
        lista.add(producto);
        guardarLista(lista);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        return cargarLista().stream().filter(p -> p.getCodigo() == codigo).findFirst().orElse(null);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : cargarLista()) {
            if (p.getNombre().startsWith(nombre)) lista.add(p);
        }
        return lista;
    }

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

    @Override
    public void eliminar(int codigo) {
        List<Producto> lista = cargarLista();
        lista.removeIf(p -> p.getCodigo() == codigo);
        guardarLista(lista);
    }

    @Override
    public boolean modificar(Producto producto) {
        actualizar(producto);
        return true;
    }

    @Override
    public List<Producto> listarTodos() {
        return cargarLista();
    }
}