package ec.edu.ups.dao.impl;
import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;

import java.util.ArrayList;
import java.util.List;

public class PreguntasDAOMemoria implements PreguntasDAO {

    private List<Preguntas> listaPreguntas = new ArrayList<>();


    public PreguntasDAOMemoria() {
        listaPreguntas = new ArrayList<>();

        listaPreguntas.add(new Preguntas("valeria123", "¿Color favorito?", "azul"));
        listaPreguntas.add(new Preguntas("juanito99", "¿Nombre de tu mascota?", "firulais"));
        listaPreguntas.add(new Preguntas("ana2025", "¿Comida favorita?", "pizza"));
        listaPreguntas.add(new Preguntas("mario12", "¿Nombre de tu mejor amigo?", "andres"));
        listaPreguntas.add(new Preguntas("admin", "¿Lugar de nacimiento?", "quito"));
        listaPreguntas.add(new Preguntas("diana", "¿Nombre de tu escuela primaria?", "comil"));
        listaPreguntas.add(new Preguntas("user", "¿Deporte favorito?", "futbol"));
    }

    @Override
    public void guardar(Preguntas preguntas) {
        for (int i = 0; i < listaPreguntas.size(); i++) {
            if (listaPreguntas.get(i).getUsername().equals(preguntas.getUsername())) {
                listaPreguntas.set(i, preguntas);
                return;
            }
        }
        listaPreguntas.add(preguntas);
    }
    @Override
    public Preguntas buscarPorUsername(String username) {
        for (Preguntas p : listaPreguntas) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }
}
