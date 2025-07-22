package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.modelo.Pregunta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación que utiliza archivos de texto para almacenar datos.
 */
public class RecuperacionDAOTexto implements RecuperacionDAO {

    private final File archivoPreguntas;

    public RecuperacionDAOTexto(String rutaArchivo) {
        this.archivoPreguntas = new File(rutaArchivo);
        File carpeta = archivoPreguntas.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try {
            if (!archivoPreguntas.exists()) {
                archivoPreguntas.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo de preguntas", e);
        }
    }

    /**
     * Guarda una nueva pregunta o actualiza una existente según el nombre de usuario.
     * Si ya existe una pregunta para el usuario, se reemplaza.
     * @param pregunta Objeto Pregunta que contiene el username, la pregunta y la respuesta
     */
    @Override
    public void guardar(Pregunta pregunta) {
        List<Pregunta> preguntas = listarTodos();
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
        guardarTodasPreguntas(preguntas);
    }

    /**
     * Busca una pregunta de recuperación por nombre de usuario.
     * @param username El nombre de usuario
     * @return La pregunta correspondiente, o null si no se encuentra
     */
    @Override
    public Pregunta buscarPorUsername(String username) {
        List<Pregunta> preguntas = listarTodos();
        for (Pregunta p : preguntas) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Carga todas las preguntas almacenadas desde el archivo de texto.
     *  * @return Lista de preguntas recuperadas
     */
    private List<Pregunta> listarTodos() {
        List<Pregunta> preguntas = new ArrayList<>();
        if (!archivoPreguntas.exists()) return preguntas;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoPreguntas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    preguntas.add(new Pregunta(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo preguntas desde archivo de texto", e);
        }
        return preguntas;
    }

    /**
     * Escribe toda la lista de preguntas en el archivo, sobrescribiendo su contenido anterior.
     *
     * @param preguntas Lista de preguntas a guardar
     */
    private void guardarTodasPreguntas(List<Pregunta> preguntas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPreguntas, false))) {
            for (Pregunta p : preguntas) {
                bw.write(p.getUsername() + "|" + p.getPregunta() + "|" + p.getRespuesta());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error guardando preguntas en archivo de texto", e);
        }
    }
}
