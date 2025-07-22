package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria del DAO para el Carrito.
 */
public class CarritoDAOMemoria implements CarritoDAO {

    private List<Carrito> carritos;
    private int codigoCarrito = 1;

    /**
     * Constructor que inicializa la lista de carritos.
     */

    public CarritoDAOMemoria() {
        this.carritos = new ArrayList<Carrito>();
    }

    /**
     * Crear nuevo carrito y agrega a la lista.
     * @param carrito a crear
     */
    @Override
    public void crear(Carrito carrito) {
        carrito.setCodigo(codigoCarrito++);
        carritos.add(carrito);
    }

    /**
     * Busca un carrito por su código.
     * @param codigo del carrito a buscar
     * @return carrito encontrado o null si no existe
     */

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    /**
     * Actualiza un carrito existente.
     * @param carrito el carrito con datos actualizados
     */
    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    /**
     * Elimina un carrito por su código.
     * @param codigo el codigo del carrito a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Carrito> iterator = carritos.iterator();
        while (iterator.hasNext()) {
            Carrito carrito = iterator.next();
            if (carrito.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    /**
     * Lista a todos los carritos.
     * @return una lista con todos los carritos
     */
    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }


    /**
     * Lista todos los carritos asociados a un usuario específico.
     * @param username el nombre de usuario
     * @return lista de carritos del usuario especificado
     */
    @Override
    public List<Carrito> listarPorUsuario(String username) {
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario() != null && carrito.getUsuario().getUsername().equals(username)) {
                resultado.add(carrito);
            }
        }
        return resultado;
    }
}
