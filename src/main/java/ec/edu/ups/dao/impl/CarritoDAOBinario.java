package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz que utiliza archivos binarios para almacenar la informacion de los carritos.
 */
public class CarritoDAOBinario implements CarritoDAO {

    private final File archivoCarritos;
    private final UsuarioDAO usuarioDAO;
    private final ProductoDAO productoDAO;

    private List<Carrito> carritos;

    public CarritoDAOBinario(String rutaCarpeta, UsuarioDAO usuarioDAO, ProductoDAO productoDAO) {
        this.archivoCarritos = new File(rutaCarpeta, "carritos.dat");
        this.usuarioDAO = usuarioDAO;
        this.productoDAO = productoDAO;

        File carpeta = archivoCarritos.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
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

    /**
     * Carga la lista de carritos desde el archivo binario.
     * @return Lista de carritos almacenados
     */
    @SuppressWarnings("unchecked")
    private List<Carrito> cargarCarritos() {
        if (archivoCarritos.length() == 0) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoCarritos))) {
            return (List<Carrito>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error leyendo carritos desde archivo binario", e);
        }
    }

    /**
     * Guarda la lista de carritos actual en el archivo binario.
     */
    private void guardarCarritos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoCarritos))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando carritos en archivo binario", e);
        }
    }

    /**
     * Crea un nuevo carrito y lo agrega a la lista, asignando un código autómatico.
     * @param carrito Objeto Carrito a registar
     */
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

    /**
     * Busca un carrito por su código.
     * @param codigo Código del carrito
     * @return Carrito encontrado o null si no existe
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) return c;
        }
        return null;
    }

    /**
     * Actualiza un carrito existente en la lista.
     * @param carrito Carrito con la información actualizada
     */
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

    /**
     * Elimina un carrito de la lista por su código.
     * @param codigo Código del carrito a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos();
    }

    /**
     * Lista todos los carritos almacenados.
     * @return
     */
    @Override
    public List<Carrito> listarTodos() {
        return new ArrayList<>(carritos);
    }

    /**
     * Lista los carritos pertenecientes a un usuario específico.
     * @param username Nombre de usuario asociado a los carritos
     * @return Lista de carritos del usuario
     */
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
