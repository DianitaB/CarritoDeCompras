package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación que almacena contenido en archivos binarios.
 */
public class UsuarioDAOBinario implements UsuarioDAO {

    private final File archivoUsuarios;

    public UsuarioDAOBinario(String rutaCarpeta) {
        this.archivoUsuarios = new File(rutaCarpeta, "C:/temp/datos");

        File carpeta = archivoUsuarios.getParentFile();  // obtiene la carpeta
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try {
            if (!archivoUsuarios.exists()) {
                archivoUsuarios.createNewFile();
                guardarUsuarios(new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo de usuarios", e);
        }
    }

    /**
     * Carga todos los usuarios desde el archivo binario.
     *
     * @return Lista de usuarios
     */
    private List<Usuario> cargarUsuarios() {
        if (archivoUsuarios.length() == 0) {
            return new ArrayList<>();
        }
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivoUsuarios))) {
            return (List<Usuario>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error leyendo usuarios desde archivo binario", e);
        }
    }

    /**
     * Guarda una lista de usuarios en el archivo binario, sobrescribiendo el contenido actual.
     *
     * @param listaUsuarios Lista de usuarios a guardar
     */
    private void guardarUsuarios(List<Usuario> listaUsuarios) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivoUsuarios))) {
            salida.writeObject(listaUsuarios);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando usuarios en archivo binario", e);
        }
    }

    /**
     * Autentica un usuario por su username y contraseña.
     * @param username  Nombre de usuario
     * @param contrasenia Contraseña
     * @return Usuario autenticado o null si no coincide
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        List<Usuario> usuarios = cargarUsuarios();
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia))
                .findFirst();
        return usuario.orElse(null);
    }

    /**
     * Crea un nuevo usuario y lo guarda en el archivo.
     * @param usuario Usuario a crear
     */
    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @param username Nombre de usuario
     * @return Usuario encontrado o null si no existe
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        List<Usuario> usuarios = cargarUsuarios();
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
        return usuario.orElse(null);
    }

    /**
     * Elimina un usuario del archivo por su username.
     * @param username Nombre de usuario a eliminar
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarUsuarios(usuarios);
    }

    /**
     * Actualiza los datos de un usuario existente.
     * @param usuario Usuario con datos actualizados
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = cargarUsuarios();
        boolean actualizado = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                actualizado = true;
                break;
            }
        }
        if (!actualizado) {
            throw new RuntimeException("No se encontró el usuario para actualizar.");
        }
        guardarUsuarios(usuarios);
    }

    /**
     * Lista todos los usuarios almacenados.
     * @return Lista de todos los usuarios
     */
    @Override
    public List<Usuario> listarTodos() {
        return cargarUsuarios();
    }

    /**
     * Lista los usuarios que pertenecen a un rol específico.
     * @param rol Rol a filtrar
     * @return Lista de usuarios con el rol dado
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuarios = cargarUsuarios();
        return usuarios.stream()
                .filter(u -> u.getRol() != null && u.getRol().equals(rol))
                .collect(Collectors.toList());
    }
}
