package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

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
        super("Eliminar Usuarios", true,true,false,true);
        this.mensajeI = mensajeI;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(300, 190);
        cambiarIdi();
    }
    public void cambiarIdi() {

    }
}
