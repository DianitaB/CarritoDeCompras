package ec.edu.ups.vista.preguntas;

import ec.edu.ups.controlador.PreguntasController;

import javax.swing.*;

public class CuestionarioView extends JInternalFrame {
    private JTextField txtUsuarioRecu;
    private JButton btnValidarRecu;
    private JComboBox cbxPreguntas;
    private JTextField txtRespuestaRecu;
    private JButton btnVerificarRecu;
    private JLabel lblUsuarioRecu;
    private JLabel lblPreguntasRecu;
    private JLabel lblRespuestaRec;
    private PreguntasController controlador;

    public CuestionarioView (){

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

    public void dispose() {
    }
}
