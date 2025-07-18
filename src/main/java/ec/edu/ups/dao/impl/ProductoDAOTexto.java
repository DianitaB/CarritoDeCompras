package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOTexto implements ProductoDAO {

    private final File archivo;

    public ProductoDAOTexto(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        try {
            if (!archivo.exists()) archivo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Error creando archivo de texto de producto", e);
        }
    }

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

    @Override
    public void crear(Producto producto) {
        List<Producto> lista = leerTodos();
        lista.add(producto);
        escribirTodos(lista);
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        return leerTodos().stream().filter(p -> p.getCodigo() == codigo).findFirst().orElse(null);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : leerTodos()) {
            if (p.getNombre().startsWith(nombre)) lista.add(p);
        }
        return lista;
    }

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

    @Override
    public void eliminar(int codigo) {
        List<Producto> lista = leerTodos();
        lista.removeIf(p -> p.getCodigo() == codigo);
        escribirTodos(lista);
    }

    @Override
    public boolean modificar(Producto producto) {
        actualizar(producto);
        return true;
    }

    @Override
    public List<Producto> listarTodos() {
        return leerTodos();
    }
}