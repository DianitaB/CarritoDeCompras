package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String username;
    private String contrasenia;
    private Rol rol;
    private String nombreCompleto;
    private String fechaNacimiento;
    private String correo;
    private String telefono;
    private List<String> preguntas;
    private List<String> respuestas;


    public Usuario(String usuarioT, String contrasenia) {

    }
    public Usuario(String username, String contrasenia, Rol rol) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Usuario(String username, String contrasenia, String nombreCompleto, String fechaNacimiento,
                   String correo, String telefono, List<String> preguntas, List<String> respuestas) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.telefono = telefono;
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        this.rol = Rol.USUARIO;

    }
    public Usuario(String username, String contrasenia, Rol rol,
                   String nombreCompleto, String fechaNacimiento,
                   String correo, String telefono) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.telefono = telefono;
        this.preguntas = new ArrayList<>();
        this.respuestas = new ArrayList<>();
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<String> preguntas) {
        this.preguntas = preguntas;
    }

    public List<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "nombreDeUsuario='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                '}';
    }
}