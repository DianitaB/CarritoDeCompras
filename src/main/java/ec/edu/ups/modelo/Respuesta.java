package ec.edu.ups.modelo;

/**
 * Representa una respuesta a una pregunta de seguridad,
 * asociando la pregunta y la respuesta proporcionada.
 */
public class Respuesta {
    private Pregunta pregunta;
    private String respuesta;

    /**
     * Constructor que inicializa la respuesta con una pregunta y la respuesta correspondiente.
     * @param pregunta Pregunta asociada.
     * @param respuesta Respuesta dada.
     */
    public Respuesta(Pregunta pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    /**
     * Obtiene la pregunta asociada a esta respuesta.
     * @return Pregunta asociada.
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * Establece la pregunta asociada a esta respuesta.
     * @param pregunta Nueva pregunta.
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Obtiene la respuesta almacenada.
     * @return Respuesta almacenada.
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la respuesta para esta pregunta.
     * @param respuesta Nueva respuesta.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Verifica si una respuesta dada es correcta comparándola
     * con la respuesta almacenada.
     * @param respuestaAValidar Respuesta a validar.
     * @return true si la respuesta es correcta, false en caso contrario o si alguna respuesta es nula.
     */
    public boolean respuestaCorrecta(String respuestaAValidar) {
        if (this.respuesta == null || respuestaAValidar == null) {
            return false;
        }
        return this.respuesta.trim().equalsIgnoreCase(respuestaAValidar.trim());
    }
}
