package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

/**
 * Interfaz para la gestión de preguntas de recuperación de contraseña.
 * Define operaciones para guardar u buscar preguntas asociadas a un usuario.
 * Interfaz que permite abstraer la lógica de acceso a los datos relacionados con la
 * recuperación de cuentas mediante preguntas de seguridad.
 */
public interface RecuperacionDAO {

    /**
     * Guarda una nueva pregunta o actualiza una existente si el username ya está registrado.
     * @param preguntas Objeto Pregunta que contiene el username, la pregunta y la respuesta
     */
    void guardar(Pregunta preguntas);

    /**
     * Busca una pregunta de seguridad asociada a un nombre de usuario.
     * @param username Nombre de usuario
     * @return Objeto Pregunta correspondiente al usuario, o null si no se encuentra
     */
    Pregunta buscarPorUsername(String username);
}
