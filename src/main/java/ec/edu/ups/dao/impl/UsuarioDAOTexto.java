package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOTexto implements UsuarioDAO {

    private final File archivo;

    public UsuarioDAOTexto(String ruta) {
        this.archivo = new File(ruta);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo de texto de usuarios", e);
        }
    }

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

    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.add(usuario);
        guardarUsuarios(usuarios);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return cargarUsuarios().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        return cargarUsuarios().stream()
                .filter(u -> u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia))
                .findFirst()
                .orElse(null);
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
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        guardarUsuarios(usuarios);
    }

    @Override
    public List<Usuario> listarTodos() {
        return cargarUsuarios();
    }

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
