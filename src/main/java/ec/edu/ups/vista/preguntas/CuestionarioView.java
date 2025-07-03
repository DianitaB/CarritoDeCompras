package ec.edu.ups.vista.preguntas;

import ec.edu.ups.controlador.PreguntasController;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.net.URL;

public class CuestionarioView extends JFrame {
    private JTextField txtUsuarioRecu;
    private JButton btnValidarRecu;
    private JComboBox cbxPreguntas;
    private JTextField txtRespuestaRecu;
    private JButton btnVerificarRecu;
    private JLabel lblUsuarioRecu;
    private JLabel lblPreguntasRecu;
    private JLabel lblRespuestaRec;
    private JPanel panelPrincipal;
    private PreguntasController controlador;

    public CuestionarioView (){
        setContentPane(panelPrincipal);
        setTitle("Registrarse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        URL btValidar = LoginView.class.getClassLoader().getResource("imagenes/validar.png");
        if (btValidar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btValidar);
            btnValidarRecu.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Validar");
        }

        URL btVerificar = LoginView.class.getClassLoader().getResource("imagenes/verificar.png");
        if (btVerificar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btVerificar);
            btnVerificarRecu.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Validar");
        }

    }
    private void btnValidarRecuActionPerformed(java.awt.event.ActionEvent evt) {
        String usuario = getTxtUsuarioRecu().getText();
        if (controlador.validarUsuario(usuario)) {
            String pregunta = controlador.obtenerPregunta();
            getCbxPreguntas().setSelectedItem(pregunta);
            JOptionPane.showMessageDialog(this, "Usuario encontrado, responda la pregunta.");
        }
    }

    public JTextField getTxtUsuarioRecu() {
        return txtUsuarioRecu;
    }

    public void setTxtUsuarioRecu(JTextField txtUsuarioRecu) {
        this.txtUsuarioRecu = txtUsuarioRecu;
    }

    public JButton getBtnValidarRecu() {
        return btnValidarRecu;
    }

    public void setBtnValidarRecu(JButton btnValidarRecu) {
        this.btnValidarRecu = btnValidarRecu;
    }

    public JTextField getTxtRespuestaRecu() {
        return txtRespuestaRecu;
    }

    public void setTxtRespuestaRecu(JTextField txtRespuestaRecu) {
        this.txtRespuestaRecu = txtRespuestaRecu;
    }

    public JComboBox getCbxPreguntas() {
        return cbxPreguntas;
    }

    public void setCbxPreguntas(JComboBox cbxPreguntas) {
        this.cbxPreguntas = cbxPreguntas;
    }

    public JButton getBtnVerificarRecu() {
        return btnVerificarRecu;
    }

    public void setBtnVerificarRecu(JButton btnVerificarRecu) {
        this.btnVerificarRecu = btnVerificarRecu;
    }

    public JLabel getLblRespuestaRec() {
        return lblRespuestaRec;
    }

    public void setLblRespuestaRec(JLabel lblRespuestaRec) {
        this.lblRespuestaRec = lblRespuestaRec;
    }

    public JLabel getLblPreguntasRecu() {
        return lblPreguntasRecu;
    }

    public void setLblPreguntasRecu(JLabel lblPreguntasRecu) {
        this.lblPreguntasRecu = lblPreguntasRecu;
    }

    public JLabel getLblUsuarioRecu() {
        return lblUsuarioRecu;
    }

    public void setLblUsuarioRecu(JLabel lblUsuarioRecu) {
        this.lblUsuarioRecu = lblUsuarioRecu;
    }
    public void setControlador(PreguntasController controlador) {
        this.controlador = controlador;
    }

    public void dispose() {
    }
}
