package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que implementa la interfaz UsuarioDAO.
 * Sigue el patron Singleton para garantizar una sola instancia.
 */
public class UsuarioDAOMemoria implements UsuarioDAO {

    private static UsuarioDAOMemoria instancia;
    private List<Usuario> usuarios;

    /**
     * Inicializa la lista con algunos usuarios predefinidos.
     */
    public UsuarioDAOMemoria() {
        usuarios = new ArrayList<Usuario>();
        crear(new Usuario("admin",
                "12345",
                Rol.ADMINISTRADOR,
                "Diana Valeria Borja Sarmiento",
                "10/07/2006",
                "valeria10diana@gmail.com",
                "0994664467"));

        crear(new Usuario("diana", "0107271108", Rol.ADMINISTRADOR));
        crear(new Usuario("user", "12345", Rol.USUARIO));
    }

    /**
     * Método estático que retorna la única instancia de la clase.
     * Si no existe, la crea.
     * @return Instancia única de UsuarioDAOMemoria
     */
    public static UsuarioDAOMemoria getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAOMemoria();
        }
        return instancia;
    }

    /**
     * Autentica un usuario verificando su username y contraseña.
     * @param username Nombre de usuario
     * @param contrasenia Contraseña del usuario
     * @return Usuario autenticado o null si no existe coincidencia
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername() != null && usuario.getContrasenia() != null &&
                    usuario.getUsername().equals(username) &&
                    usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Crea un nuevo usuario y lo agrega a la lista.
     * @param usuario Objeto Usuario a guardar
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @param username Nombre de usuario a buscar
     * @return Usuario encontrado o null si no existe
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario de la lista según su username.
     * @param username Nombre de usuario a eliminar
     */
    @Override
    public void eliminar(String username) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUsername().equals(username)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Actualiza los datos de un usuario ya existente.
     * Busca por username y reemplaza el objeto correspondiente.
     * @param usuario Objeto Usuario actualizado.
     */
    @Override
    public void actualizar(Usuario usuario) {
        for(int i = 0; i < usuarios.size(); i++){
            Usuario usuarioAux = usuarios.get(i);
            if(usuarioAux.getUsername().equals(usuario.getUsername())){
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    /**
     * Lista todos los usuarios almacenados en memoria.
     * @return Lista de usuarios
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    /**
     * Lista los usuarios segun su rol.
     * @param rol Rol que se desea filtrar
     * @return Lista de usuarios con el rol indicado
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuariosEncontrados = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getRol().equals(rol)) {
                usuariosEncontrados.add(usuario);
            }
        }
        return usuariosEncontrados;
    }
}
