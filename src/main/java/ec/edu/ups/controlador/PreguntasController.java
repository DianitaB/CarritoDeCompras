package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.preguntas.CuestionarioRecuView;
import ec.edu.ups.vista.preguntas.CuestionarioView;

import javax.swing.*;

public class PreguntasController {

    private final CuestionarioView cuestionarioView;
    private final CuestionarioRecuView cuestionarioRecuView;
    private final PreguntasDAO preguntasDAO;
    private MensajeInternacionalizacionHandler mensajeI;

    private Preguntas preguntaActual;

    public PreguntasController(CuestionarioView cuestionarioView, CuestionarioRecuView cuestionarioRecuView, PreguntasDAO preguntasDAO,MensajeInternacionalizacionHandler mensajeI) {
        this.cuestionarioView = cuestionarioView;
        this.cuestionarioRecuView = cuestionarioRecuView;
        this.preguntasDAO = preguntasDAO;
        this.mensajeI = mensajeI;
        configurarEventos();
    }

    private void configurarEventos() {
        cuestionarioView.getBtnValidarRecu().addActionListener(e -> {
            String username = cuestionarioView.getTxtUsuarioRecu().getText();
            if (validarUsuario(username)) {
                cuestionarioView.getCbxPreguntas().removeAllItems();
                cuestionarioView.getCbxPreguntas().addItem(preguntaActual.getPregunta());
                JOptionPane.showMessageDialog(cuestionarioView, "Usuario encontrado, responda la pregunta.");
            } else {
                JOptionPane.showMessageDialog(cuestionarioView, "Usuario no encontrado.");
            }
        });

        cuestionarioView.getBtnVerificarRecu().addActionListener(e -> {
            String respuesta = cuestionarioView.getTxtRespuestaRecu().getText();
            if (preguntaActual != null && preguntaActual.getRespuesta().equalsIgnoreCase(respuesta.trim())) {
                JOptionPane.showMessageDialog(cuestionarioView, "Respuesta correcta. Puede cambiar su contraseña.");
                cuestionarioRecuView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(cuestionarioView, "Respuesta incorrecta.");
            }
        });

        cuestionarioRecuView.getBtnGuardarNueva().addActionListener(e -> {
            String nueva = new String(cuestionarioRecuView.getPswNueva().getPassword());
            String confirmar = new String(cuestionarioRecuView.getPswConfContra().getPassword());

            if (nueva.isEmpty() || confirmar.isEmpty()) {
                JOptionPane.showMessageDialog(cuestionarioRecuView, "Complete ambos campos.");
                return;
            }

            if (!nueva.equals(confirmar)) {
                JOptionPane.showMessageDialog(cuestionarioRecuView, "Las contraseñas no coinciden.");
                return;
            }

            if (preguntaActual != null) {
                preguntaActual.setPassword(nueva);
                preguntasDAO.guardar(preguntaActual);
                JOptionPane.showMessageDialog(cuestionarioRecuView, "Contraseña actualizada correctamente.");
                cuestionarioRecuView.dispose();
                cuestionarioView.dispose();
            }
        });
    }

    public boolean validarUsuario(String username) {
        preguntaActual = preguntasDAO.buscarPorUsername(username);
        return preguntaActual != null;
    }

    public String obtenerPregunta() {
        return preguntaActual != null ? preguntaActual.getPregunta() : null;
    }
}
