package ec.edu.ups.controlador;

import ec.edu.ups.dao.RecuperacionDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.preguntas.CuestionarioRecuView;
import ec.edu.ups.vista.preguntas.CuestionarioView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecuperacionController {

    private final CuestionarioView cuestionarioView;
    private final CuestionarioRecuView cuestionarioRecuView;
    private final RecuperacionDAO preguntasDAO;
    private final UsuarioController usuarioController;
    private MensajeInternacionalizacionHandler mensajeI;
    private Pregunta preguntaActual;

    public RecuperacionController(CuestionarioView cuestionarioView,
                                  CuestionarioRecuView cuestionarioRecuView,
                                  RecuperacionDAO preguntasDAO,
                                  MensajeInternacionalizacionHandler mensajeI,
                                  UsuarioController usuarioController) {
        this.cuestionarioView = cuestionarioView;
        this.cuestionarioRecuView = cuestionarioRecuView;
        this.preguntasDAO = preguntasDAO;
        this.mensajeI = mensajeI;
        this.usuarioController = usuarioController;
        configurarEventos();
    }

    private void configurarEventos() {
        cuestionarioView.getBtnValidarRecu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarRecu();
            }
        });
        cuestionarioView.getBtnVerificarRecu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarUsuario();
            }
        });
        cuestionarioRecuView.getBtnGuardarNueva().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });
    }

    private void verificarUsuario() {
        String respuesta = cuestionarioView.getTxtRespuestaRecu().getText();
        if (preguntaActual != null && preguntaActual.getRespuesta().equalsIgnoreCase(respuesta.trim())) {
            JOptionPane.showMessageDialog(cuestionarioView, mensajeI.get("respuesta.correcta"));
        } else {
            JOptionPane.showMessageDialog(cuestionarioView, mensajeI.get("respuesta.incorrecta"));
        }
    }
    private void guardarUsuario(){
        String nueva = new String(cuestionarioRecuView.getPswNueva().getPassword());
        String confirmar = new String(cuestionarioRecuView.getPswConfContra().getPassword());

        if (nueva.isEmpty() || confirmar.isEmpty()) {
            JOptionPane.showMessageDialog(cuestionarioRecuView, mensajeI.get("campos.completar"));
            return;
        }

        if (!nueva.equals(confirmar)) {
            JOptionPane.showMessageDialog(cuestionarioRecuView, mensajeI.get("contrasenas.no.coinciden"));
            return;
        }

        if (preguntaActual != null) {
            preguntaActual.setPassword(nueva);
            preguntasDAO.guardar(preguntaActual);
            JOptionPane.showMessageDialog(cuestionarioRecuView, mensajeI.get("contrasena.actualizada"));
            cuestionarioRecuView.dispose();
            cuestionarioView.dispose();
        }
    }
    private void validarRecu(){
        String username = cuestionarioView.getTxtUsuarioRecu().getText();
        if (validarUsuario(username)) {
            cuestionarioView.getCbxPreguntas().removeAllItems();
            cuestionarioView.getCbxPreguntas().addItem(preguntaActual.getPregunta());

            JOptionPane.showMessageDialog(cuestionarioView, mensajeI.get("usuario.encontrado"));
        } else {
            JOptionPane.showMessageDialog(cuestionarioView, mensajeI.get("usuario.no.encontrado"));
        }
    }
    public boolean validarUsuario(String username) {
        preguntaActual = preguntasDAO.buscarPorUsername(username);
        return preguntaActual != null;
    }


    public String obtenerPregunta() {
        return preguntaActual != null ? preguntaActual.getPregunta() : null;
    }
}