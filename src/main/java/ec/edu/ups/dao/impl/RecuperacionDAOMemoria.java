package ec.edu.ups.dao.impl;
import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class RecuperacionDAOMemoria implements RecuperacionDAO {

    private List<Pregunta> listaPreguntas = new ArrayList<>();


    public RecuperacionDAOMemoria() {
        listaPreguntas = new ArrayList<>();

        listaPreguntas.add(new Pregunta("valeria123", "¿Color favorito?", "azul"));
        listaPreguntas.add(new Pregunta("juanito99", "¿Nombre de tu mascota?", "firulais"));
        listaPreguntas.add(new Pregunta("ana2025", "¿Comida favorita?", "pizza"));
        listaPreguntas.add(new Pregunta("mario12", "¿Nombre de tu mejor amigo?", "andres"));
        listaPreguntas.add(new Pregunta("admin", "¿Lugar de nacimiento?", "quito"));
        listaPreguntas.add(new Pregunta("diana", "¿Nombre de tu escuela primaria?", "comil"));
        listaPreguntas.add(new Pregunta("user", "¿Deporte favorito?", "futbol"));
    }

    @Override
    public void guardar(Pregunta preguntas) {
        for (int i = 0; i < listaPreguntas.size(); i++) {
            if (listaPreguntas.get(i).getUsername().equals(preguntas.getUsername())) {
                listaPreguntas.set(i, preguntas);
                return;
            }
        }
        listaPreguntas.add(preguntas);
    }
    @Override
    public Pregunta buscarPorUsername(String username) {
        for (Pregunta p : listaPreguntas) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }
}
