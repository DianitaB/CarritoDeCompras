package ec.edu.ups.dao;

import ec.edu.ups.modelo.Preguntas;

public interface PreguntasDAO {
    void guardar(Preguntas preguntas);
    Preguntas buscarPorUsername(String username);
}
