package ec.edu.ups.modelo;

public class Preguntas {
    private String username;
    private String pregunta;
    private String respuesta;
    private String password;

    public Preguntas(String username, String pregunta, String respuesta) {
        this.username = username;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.password = "1234";
    }

    public String getUsername() {
        return username;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
