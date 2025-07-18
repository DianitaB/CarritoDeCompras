package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

/**
 * Interfaz DAO para la gestión de usuarios.
 */
public interface UsuarioDAO {

    /**
     * Verifica las credenciales del usuario.
     * @param username Nombre de usuario
     * @param contrasenia Contraseña del usuario
     * @return Usuario autenticado o null si las credenciales son incorrectas
     */
    Usuario autenticar(String username, String contrasenia);

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario Objeto Usuario a crear
     */
    void crear(Usuario usuario);

    /**
     * Busca un usuario por su nombre de usuario
     * @param username Nombre de usuario
     * @return Objeto Usuario encontrado o null si no existe
     */
    Usuario buscarPorUsername(String username);

    /**
     * Elimina un usuario del sistema utilizando su username.
     * @param username NOmbre de usuario a eliminar
     */
    void eliminar(String username);

    /**
     * Actualiza los datos de un usuario existente.
     * @param usuario Objeto Usuario con los datos actualizados.
     */
    void actualizar(Usuario usuario);

    /**
     * Retorna una lista con todos los usuarios registrados.
     * @return Lista de usuarios
     */
    List<Usuario> listarTodos();

    /**
     * Retorna una lista de usuarios filtrados por rol
     * @param rol Rol a filtrar
     * @return Lista de usuarios que tienen el rol especificado
     */
    List<Usuario> listarPorRol(Rol rol);

}