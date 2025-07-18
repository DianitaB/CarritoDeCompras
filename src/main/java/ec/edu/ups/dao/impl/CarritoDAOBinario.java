package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOBinario implements CarritoDAO {

    private final File archivoCarritos;
    private final UsuarioDAO usuarioDAO;
    private final ProductoDAO productoDAO;

    private List<Carrito> carritos;

    public CarritoDAOBinario(String rutaArchivo, UsuarioDAO usuarioDAO, ProductoDAO productoDAO) {
        this.archivoCarritos = new File(rutaArchivo);
        this.usuarioDAO = usuarioDAO;
        this.productoDAO = productoDAO;

        if (!archivoCarritos.exists()) {
            try {
                archivoCarritos.getParentFile().mkdirs();
                archivoCarritos.createNewFile();
                carritos = new ArrayList<>();
                guardarCarritos();
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear el archivo de carritos", e);
            }
        } else {
            carritos = cargarCarritos();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Carrito> cargarCarritos() {
        if (archivoCarritos.length() == 0) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoCarritos))) {
            return (List<Carrito>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error leyendo carritos desde archivo binario", e);
        }
    }

    private void guardarCarritos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoCarritos))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando carritos en archivo binario", e);
        }
    }

    @Override
    public void crear(Carrito carrito) {
        int codigoMax = carritos.stream()
                .mapToInt(Carrito::getCodigo)
                .max()
                .orElse(0);
        carrito.setCodigo(codigoMax + 1);
        carritos.add(carrito);
        guardarCarritos();
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) return c;
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                guardarCarritos();
                return;
            }
        }
        throw new RuntimeException("No se encontró carrito para actualizar");
    }

    @Override
    public void eliminar(int codigo) {
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos();
    }

    @Override
    public List<Carrito> listarTodos() {
        return new ArrayList<>(carritos);
    }

    @Override
    public List<Carrito> listarPorUsuario(String username) {
        List<Carrito> filtrados = new ArrayList<>();
        for (Carrito c : carritos) {
            if (c.getUsuario() != null && c.getUsuario().getUsername().equals(username)) {
                filtrados.add(c);
            }
        }
        return filtrados;
    }
}
