package ec.edu.ups.modelo;

import ec.edu.ups.modelo.Exception.CedulaValidationException;
import ec.edu.ups.modelo.Exception.UsuarioException;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String cedula;
    private String username;
    private String contrasenia;
    private Rol rol;
    private String nombreCompleto;
    private String fechaNacimiento;
    private String correo;
    private String telefono;
    private List<String> preguntas;
    private List<String> respuestas;

    public Usuario(String cedula, String username, String contrasenia, Rol rol, String nombreCompleto, String fechaNacimiento, String correo, String telefono, List<String> preguntas, List<String> respuestas) {
        this.cedula = cedula;
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.telefono = telefono;
        this.preguntas = preguntas;
        this.respuestas = respuestas;
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

    public void setCedula(String cedula) throws UsuarioException {
        if (cedula == null || cedula.isEmpty()) {
            throw new UsuarioException("La cédula no puede estar vacía");
        }
        try {
            validarCedula(cedula);
        } catch (CedulaValidationException e) {
            throw new UsuarioException("Cédula inválida: " + e.getMessage());
        }

        this.cedula = cedula;
    }

    public String getCedula() {

        return cedula;
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

    public void setContrasenia(String contrasenia) throws UsuarioException {
        StringBuilder errores = new StringBuilder();

        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            errores.append("La contraseña no puede estar vacía.\n");
        } else {
            if (contrasenia.contains(" ")) {
                errores.append("La contraseña no debe contener espacios.\n");
            }
            if (contrasenia.length() < 6) {
                errores.append("Debe tener al menos 6 caracteres.\n");
            }
            if (!contrasenia.matches(".*[A-Z].*")) {
                errores.append("Debe contener al menos una letra mayúscula.\n");
            }
            if (!contrasenia.matches(".*[a-z].*")) {
                errores.append("Debe contener al menos una letra minúscula.\n");
            }
            if (!contrasenia.matches(".*[@_\\-].*")) {
                errores.append("Debe incluir al menos uno de los siguientes caracteres: @, _ o -.\n");
            }
        }
        if (errores.length() > 0) {
            throw new UsuarioException("Errores en la contraseña:\n" + errores.toString());
        }

        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public static void validarCedula(String cedula) throws CedulaValidationException {
        if (cedula == null || cedula.length() != 10) {
            throw new CedulaValidationException("La cédula debe tener 10 dígitos.");
        }
        try {
            int provincia = Integer.parseInt(cedula.substring(0, 2));
            if (provincia < 0 || provincia > 24) {
                throw new CedulaValidationException("Código de provincia inválido.");
            }

            int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
            if (tercerDigito < 0 || tercerDigito > 5) {
                throw new CedulaValidationException("El tercer dígito es inválido.");
            }

            int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int suma = 0;

            for (int i = 0; i < coeficientes.length; i++) {
                int valor = Character.getNumericValue(cedula.charAt(i)) * coeficientes[i];
                if (valor >= 10) {
                    valor -= 9;
                }
                suma += valor;
            }

            int digitoVerificador = Character.getNumericValue(cedula.charAt(9));
            int decenaSuperior = ((suma + 9) / 10) * 10;
            int resultado = decenaSuperior - suma;

            if (resultado == 10) {
                resultado = 0;
            }

            if (resultado != digitoVerificador) {
                throw new CedulaValidationException("Cédula inválida: dígito verificador incorrecto.");
            }

        } catch (NumberFormatException e) {
            throw new CedulaValidationException("Cédula contiene caracteres inválidos.");
        }
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