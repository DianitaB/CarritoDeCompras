package ec.edu.ups.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Clase utilitaria para formatear valores como moneda y fechas
 * según la configuración regional especificada.
 */
public class FormateadorUtils {

    /**
     * Formatea un valor numérico como moneda según la localidad indicada.
     *
     * @param cantidad Valor numérico a formatear.
     * @param locale Configuración regional para el formato de moneda.
     * @return Cadena formateada con el símbolo de moneda correspondiente.
     */
    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }

    /**
     * Formatea una fecha dada en milisegundos desde la época (1970-01-01)
     * según la localidad especificada, con formato MEDIUM.
     * @param locale Configuración regional para el formato de fecha.
     * @return Cadena con la fecha formateada.
     */
    public static String formatearFecha(long fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }
}
