package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.modelo.Pregunta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación que utiliza archivo binarios para almacenar datos.
 */
public class RecuperacionDAOBinario implements RecuperacionDAO {

    private final File archivoPreguntas;

    public RecuperacionDAOBinario(String rutaCarpeta) {
        this.archivoPreguntas = new File(rutaCarpeta, "preguntas.dat");

        File carpeta = archivoPreguntas.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try {
            if (!archivoPreguntas.exists()) {
                archivoPreguntas.createNewFile();
                guardarPreguntas(new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo de preguntas", e);
        }
    }

    /**
     * Carga todas las preguntas almacenadas desde el archivo binario.
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Pregunta> cargarPreguntas() {
        if (archivoPreguntas.length() == 0) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoPreguntas))) {
            return (List<Pregunta>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error leyendo preguntas desde archivo binario", e);
        }
    }

    /**
     * Guarda una lista de preguntas en el archivo binario, sobrescribiéndolo.
     * @param preguntas
     */
    private void guardarPreguntas(List<Pregunta> preguntas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoPreguntas))) {
            oos.writeObject(preguntas);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando preguntas en archivo binario", e);
        }
    }

    /**
     * Guarda o actualiza una pregunta en el archivo.
     * @param pregunta Objeto Pregunta que contiene el username, la pregunta y la respuesta
     */
    @Override
    public void guardar(Pregunta pregunta) {
        List<Pregunta> preguntas = cargarPreguntas();
        boolean actualizado = false;
        for (int i = 0; i < preguntas.size(); i++) {
            if (preguntas.get(i).getUsername().equals(pregunta.getUsername())) {
                preguntas.set(i, pregunta);
                actualizado = true;
                break;
            }
        }
        if (!actualizado) {
            preguntas.add(pregunta);
        }
        guardarPreguntas(preguntas);
    }

    /**
     * Busca una pregunta de recuperación asociada a un nombre de usuario.
     * @param username Nombre de usuario
     * @return
     */
    @Override
    public Pregunta buscarPorUsername(String username) {
        List<Pregunta> preguntas = cargarPreguntas();
        for (Pregunta p : preguntas) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }
}
