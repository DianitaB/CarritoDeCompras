package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;

public class UsuarioListarView extends JInternalFrame{
    private JTextField txtNombreUsu;
    private JButton btnBuscar;
    private JTable tblListarUsuarios;
    private JLabel lblNombre;
    private JButton btnListar;
    private JPanel panelPrincipal;
    private MensajeInternacionalizacionHandler mensajeI;
    private DefaultTableModel modelo;

    public UsuarioListarView(MensajeInternacionalizacionHandler mensajeI) {
        super("Listar Usuarios", true,true,false,true);
        this.mensajeI = mensajeI;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        modelo = new DefaultTableModel(new Object[]{"Usuario", "Rol"}, 0);
        tblListarUsuarios.setModel(modelo);
        iconoIma();
        cambiarIdioma();
    }

    public JTextField getTxtNombreUsu() {
        return txtNombreUsu;
    }

    public void setTxtNombreUsu(JTextField txtNombreUsu) {
        this.txtNombreUsu = txtNombreUsu;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblListarUsuarios() {
        return tblListarUsuarios;
    }

    public void setTblListarUsuarios(JTable tblListarUsuarios) {
        this.tblListarUsuarios = tblListarUsuarios;
    }

    public JLabel getLblNombre() {return lblNombre;}

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
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

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void cambiarIdioma() {
        this.setTitle(mensajeI.get("listar.titulo.ventana"));
        lblNombre.setText(mensajeI.get("listar.label.nombre"));
        btnBuscar.setText(mensajeI.get("listar.boton.buscar"));
        btnListar.setText(mensajeI.get("listar.boton.listar"));
        modelo.setColumnIdentifiers(new String[] {
                mensajeI.get("listar.columna.usuario"),
                mensajeI.get("listar.columna.rol")
        });
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    private void iconoIma(){
        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconoLis = new ImageIcon(btBuscar);
            btnBuscar.setIcon(iconoLis);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar Usuario");
        }
        URL btListar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if (btListar != null) {
            ImageIcon icnoLi = new ImageIcon(btListar);
            btnListar.setIcon(icnoLi);
        } else {
            System.err.println("Error: No se ha cargado el icono de Listar Usuario");
        }
    }
}
