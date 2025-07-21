package ec.edu.ups.modelo;

/**
 * Representa una pregunta de seguridad asociada a un usuario,
 * con su respectiva respuesta y una contraseña predeterminada.
 */
public class Pregunta {
    private String username;
    private String pregunta;
    private String respuesta;
    private String password;

    /**
     * Constructor que inicializa la pregunta de seguridad con el nombre de usuario.
     * @param username Nombre de usuario asociado.
     * @param pregunta Pregunta de seguridad.
     * @param respuesta Respuesta a la pregunta.
     */
    public Pregunta(String username, String pregunta, String respuesta) {
        this.username = username;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.password = "1234";
    }

    /**
     * Obtiene el nombre de usuario asociado a la pregunta.
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtiene la pregunta de seguridad.
     * @return Pregunta.
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Obtiene la respuesta a la pregunta de seguridad.
     * @return Respuesta.
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la respuesta a la pregunta de seguridad.
     * @param respuesta Nueva respuesta.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Obtiene la contraseña asociada (por defecto "1234").
     * @return Contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece una nueva contraseña para la pregunta.
     * @param password Nueva contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
