package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.modelo.Pregunta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecuperacionDAOTexto implements RecuperacionDAO {

    private final File archivoPreguntas;

    public RecuperacionDAOTexto(String rutaArchivo) {
        archivoPreguntas = new File(rutaArchivo);
        try {
            if (!archivoPreguntas.exists()) {
                archivoPreguntas.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo de preguntas", e);
        }
    }

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
