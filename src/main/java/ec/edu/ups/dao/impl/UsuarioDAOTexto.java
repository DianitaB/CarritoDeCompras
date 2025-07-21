package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación que utiliza archivos de texto.
 */
public class UsuarioDAOTexto implements UsuarioDAO {

    private final File archivo;

    public UsuarioDAOTexto(String ruta) {
        this.archivo = new File(ruta);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo de texto de usuarios", e);
        }
    }

    /**
     * Carga todos los usuarios desde el archivo de texto.
     * @return Lista de usuarios
     */
    private List<Usuario> cargarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 3) {
                    String cedula = datos[0];
                    String contrasenia = datos[1];
                    Rol rol = Rol.valueOf(datos[2]);
                    lista.add(new Usuario(cedula, contrasenia, rol));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer desde archivo de texto", e);
        }
        return lista;
    }

    /**
     * Guarda una lista de usuarios sobrescribiendo el archivo de texto.
     * @param usuarios Lista de usuarios a guardar
     */
    private void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            for (Usuario u : usuarios) {
                String linea = u.getUsername() + ";" + u.getContrasenia() + ";" + u.getRol().name();
                escritor.write(linea);
                escritor.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en archivo de texto", e);
        }
    }

    /**
     * Crea un nuevo usuario y lo agrega al archivo.
     * @param usuario Usuario a registrar
     */
    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @param username Nombre de usuario (cedula)
     * @return Usuario si existe, o null si no se encuentra
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        return cargarUsuarios().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * Autentica un usuario verificando nombre de usuario y contraseña.
     * @param username    Nombre de usuario
     * @param contrasenia Contraseña
     * @return Usuario si las credenciales son válidas, null si no
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        return cargarUsuarios().stream()
                .filter(u -> u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia))
                .findFirst()
                .orElse(null);
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
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        guardarUsuarios(usuarios);
    }

    /**
     * Devuelve todos los usuarios registrados.
     * @return Lista de todos los usuarios
     */
    @Override
    public List<Usuario> listarTodos() {
        return cargarUsuarios();
    }

    /**
     * Devuelve una lista de usuarios que tienen un rol específico.
     * @param rol Rol a filtrar
     * @return Lista de usuarios con ese rol
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : cargarUsuarios()) {
            if (u.getRol() == rol) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }
}
