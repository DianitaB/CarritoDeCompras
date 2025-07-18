package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioDAOBinario implements UsuarioDAO {

    private final File archivoUsuarios;

    public UsuarioDAOBinario(String rutaArchivo) {
        this.archivoUsuarios = new File(rutaArchivo);
        try {
            if (!archivoUsuarios.exists()) {
                archivoUsuarios.createNewFile();
                guardarUsuarios(new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo de usuarios", e);
        }
    }

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

    private void guardarUsuarios(List<Usuario> listaUsuarios) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivoUsuarios))) {
            salida.writeObject(listaUsuarios);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando usuarios en archivo binario", e);
        }
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        List<Usuario> usuarios = cargarUsuarios();
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia))
                .findFirst();
        return usuario.orElse(null);
    }

    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        List<Usuario> usuarios = cargarUsuarios();
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
        return usuario.orElse(null);
    }

    @Override
    public void eliminar(String username) {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.removeIf(u -> u.getUsername().equals(username));
        guardarUsuarios(usuarios);
    }

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

    @Override
    public List<Usuario> listarTodos() {
        return cargarUsuarios();
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuarios = cargarUsuarios();
        return usuarios.stream()
                .filter(u -> u.getRol() != null && u.getRol().equals(rol))
                .collect(Collectors.toList());
    }
}
