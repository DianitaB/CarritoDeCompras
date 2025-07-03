package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class UsuarioEliminarView extends JInternalFrame{
    private JTextField txtUsuario;
    private JButton btnBuscarE;
    private JTextField txtContraseniaE;
    private JButton btnEliminar;
    private JLabel lblUsuario;
    private JLabel lblContrasenia;
    private JPanel panelPrincipal;
    private MensajeInternacionalizacionHandler mensajeI;

    public UsuarioEliminarView(MensajeInternacionalizacionHandler mensajeI) {
        super("Eliminar Usuarios", true,true,false,true);
        this.mensajeI = mensajeI;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(300, 190);
        cambiarIdi();
        iconosIma();
    }

    public void cambiarIdi  (){

    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JButton getBtnBuscarE() {
        return btnBuscarE;
    }

    public void setBtnBuscarE(JButton btnBuscarE) {
        this.btnBuscarE = btnBuscarE;
    }

    public JTextField getTxtContraseniaE() {
        return txtContraseniaE;
    }

    public void setTxtContraseniaE(JTextField txtContraseniaE) {
        this.txtContraseniaE = txtContraseniaE;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public MensajeInternacionalizacionHandler getMensajeI() {
        return mensajeI;
    }

    public void setMensajeI(MensajeInternacionalizacionHandler mensajeI) {
        this.mensajeI = mensajeI;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void iconosIma(){
        URL btEliminarr = LoginView.class.getClassLoader().getResource("imagen/delete.png");
        if (btEliminarr != null) {
            ImageIcon iconoEliminarU = new ImageIcon(btEliminarr);
            btnEliminar.setIcon(iconoEliminarU);
        } else {
            System.err.println("Error: No se ha cargado el icono de Eliminar Usuario");
        }
    }
}
