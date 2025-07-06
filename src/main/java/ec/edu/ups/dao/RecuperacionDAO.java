package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

public interface RecuperacionDAO {
    void guardar(Pregunta preguntas);
    Pregunta buscarPorUsername(String username);
}
