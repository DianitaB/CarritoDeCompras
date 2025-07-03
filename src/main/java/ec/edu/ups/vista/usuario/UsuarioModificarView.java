package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class UsuarioModificarView extends JInternalFrame{
    private JTextField txtUsuarioModi;
    private JButton btnBuscarModi;
    private JLabel lblUsuarioModi;
    private JTextField txtUsuarioModis;
    private JTextField txtContraseniaModi;
    private JLabel lbl2UsuarioModi;
    private JLabel lblContraModi;
    private JButton btnModificarU;
    private JPanel panelPrincipal;
    private MensajeInternacionalizacionHandler mensajeI;

    public UsuarioModificarView(MensajeInternacionalizacionHandler mensajeI) {
        super("Modificar Usuarios", true,true,false,true);
        this.mensajeI = mensajeI;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(300, 190);
        cambiarIdi();

        URL btBuscarM = LoginView.class.getClassLoader().getResource("imagen/buscar.png");
        if (btBuscarM != null) {
            ImageIcon icnoBuscar = new ImageIcon(btBuscarM);
            btnBuscarModi.setIcon(icnoBuscar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar Usuario");
        }

        URL btModificarM = LoginView.class.getClassLoader().getResource("imagen/modificar.png");
        if (btModificarM != null) {
            ImageIcon iconoModi = new ImageIcon(btModificarM);
            btnModificarU.setIcon(iconoModi);
        } else {
            System.err.println("Error: No se ha cargado el icono de Modificar Usuario");
        }
    }

    public void cambiarIdi() {

    }

    public JTextField getTxtUsuarioModi() {
        return txtUsuarioModi;
    }

    public void setTxtUsuarioModi(JTextField txtUsuarioModi) {
        this.txtUsuarioModi = txtUsuarioModi;
    }

    public JButton getBtnBuscarModi() {
        return btnBuscarModi;
    }

    public void setBtnBuscarModi(JButton btnBuscarModi) {
        this.btnBuscarModi = btnBuscarModi;
    }

    public JLabel getLblUsuarioModi() {
        return lblUsuarioModi;
    }

    public void setLblUsuarioModi(JLabel lblUsuarioModi) {
        this.lblUsuarioModi = lblUsuarioModi;
    }

    public JTextField getTxtUsuarioModis() {
        return txtUsuarioModis;
    }

    public void setTxtUsuarioModis(JTextField txtUsuarioModis) {
        this.txtUsuarioModis = txtUsuarioModis;
    }

    public JTextField getTxtContraseniaModi() {
        return txtContraseniaModi;
    }

    public void setTxtContraseniaModi(JTextField txtContraseniaModi) {
        this.txtContraseniaModi = txtContraseniaModi;
    }

    public JLabel getLbl2UsuarioModi() {
        return lbl2UsuarioModi;
    }

    public void setLbl2UsuarioModi(JLabel lbl2UsuarioModi) {
        this.lbl2UsuarioModi = lbl2UsuarioModi;
    }

    public JLabel getLblContraModi() {
        return lblContraModi;
    }

    public void setLblContraModi(JLabel lblContraModi) {
        this.lblContraModi = lblContraModi;
    }

    public JButton getBtnModificarU() {
        return btnModificarU;
    }

    public void setBtnModificarU(JButton btnModificarU) {
        this.btnModificarU = btnModificarU;
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
}
