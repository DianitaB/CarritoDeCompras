package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;

import java.util.List;

/**
 * Interfaz que define las operaciones básicas para gestionar objetos de tipo Carrito.
 * Interfaz que sigue el patrón DAO.
 */
public interface CarritoDAO {

    /**
     * Crea un nuevo carrito en el sistema.
     * @param carrito Objeto Carrito a registar
     */
    void crear(Carrito carrito);

    /**
     * Busca un carrito por su código único.
     * @param codigo Código del carrito
     * @return Objeto Carrito encontrado o null si no existe
     */
    Carrito buscarPorCodigo(int codigo);

    /**
     * Actualiza los datos de un carrito existente.
     * @param carrito Carrito con la información actualizada
     */
    void actualizar(Carrito carrito);

    /**
     * Elimina un carrito del sistema utilizando su código.
     * @param codigo Código del carrito a eliminar
     */
    void eliminar(int codigo);

    /**
     * Lista todos los carritos registrados en el sistema.
     * @return Lista de todos los carritos
     */
    List<Carrito> listarTodos();

    /**
     * Lista de todos los carritos pertenecientes a un usuario específico.
     * @param username Nombre de usuario asoiado a los carritos
     * @return Lista de carritos del usuario indicado
     */
    List<Carrito> listarPorUsuario(String username);
}