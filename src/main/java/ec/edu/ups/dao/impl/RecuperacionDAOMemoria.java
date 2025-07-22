package ec.edu.ups.dao.impl;
import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la interfaz RecuperacionDAO
 * Base de datos en memoria para almacenar preguntas de recuperacion
 *  @author Diana Borja
 */
public class RecuperacionDAOMemoria implements RecuperacionDAO {

    /**
     * Lista que alamcena todas las preguntas de recuperación en memoria.
     */
    private List<Pregunta> listaPreguntas = new ArrayList<>();

    /**
     * Constructor que inicializa la lista de algunas preguntas de ejemplo
     */
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

    /**
     * Guarda o actualiza una pregunta en la lista.
     * Si el username ya existe actualiza la pregunta existente
     * Si no existe, agrega la nueva pregunta a la lista.
     * @param preguntas que se desean guardar o actualizar
     */
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

    /**
     * Busca una pregunta asociada a un username
     * @param username Nombre de usuario que se desea buscar.
     * @return Pregunta asociada al username, o null si no se encuentra
     */
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