package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase para manejar la internacionalización de mensajes.
 */
public class MensajeInternacionalizacionHandler {

    private ResourceBundle bundle;
    private Locale locale;

    /**
     * Constructor que inicializa el handler con el lenguaje y país especificados.
     * @param lenguaje Código del lenguaje (ejemplo: "es", "en").
     * @param pais Código del país (ejemplo: "EC", "US").
     */
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Obtiene el mensaje asociado a la clave proporcionada.
     * @param key Clave del mensaje a obtener.
     * @return Mensaje localizado correspondiente a la clave.
     */
    public String get(String key) {
        return bundle.getString(key);
    }

    /**
     * Cambia el lenguaje y país usados para la internacionalización.
     * @param lenguaje Nuevo código de lenguaje.
     * @param pais Nuevo código de país.
     */
    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Obtiene la configuración regional actual (Locale).
     * @return Locale actual usado para los mensajes.
     */
    public Locale getLocale() {
        return locale;
    }

}
