package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalView extends JFrame {

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    private JMenuBar menuBar;

    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenu menuIdioma;
    private JMenu menuCerrarSesion;

    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemModificarProducto;
    private JMenuItem menuItemBuscarProducto;

    private JMenuItem menuItemAñadirCarrito;
    private JMenuItem menuItemBuscarCarrito;
    private JMenuItem menuItemEliminarCarrito;
    private JMenuItem menuItemModificarCarrito;

    private JMenuItem menuItemEliminarUsuario;
    private JMenuItem menuItemModificarUsuario;
    private JMenuItem menuItemBuscarUsuario;

    private JMenuItem menuItemIdiomaEspanol;
    private JMenuItem menuItemIdiomaIngles;
    private JMenuItem menuItemIdiomaFrances;

    private JMenuItem menuItemCerrarSesion;

    private JDesktopPane jDesktopPane;
    private JLabel lblUsuario;


    public MenuPrincipalView(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
        initComponents();

    }

    public void configurarOpcionesPorRolUsuario(String rol) {
        if ("ADMIN".equalsIgnoreCase(rol) || "ADMINISTRADOR".equalsIgnoreCase(rol)) {
            menuUsuario.setVisible(true);
            menuProducto.setVisible(true);
            menuCarrito.setVisible(true);
            menuCerrarSesion.setVisible(true);
            menuIdioma.setVisible(true);

            menuUsuario.setEnabled(true);
            menuProducto.setEnabled(true);
            menuCarrito.setEnabled(true);
            menuCerrarSesion.setEnabled(true);
            menuIdioma.setEnabled(true);

            menuItemModificarUsuario.setEnabled(true);
            menuItemEliminarUsuario.setEnabled(true);
            menuItemBuscarUsuario.setEnabled(true);

            menuItemCrearProducto.setEnabled(true);
            menuItemEliminarProducto.setEnabled(true);
            menuItemModificarProducto.setEnabled(true);
            menuItemBuscarProducto.setEnabled(true);

            menuItemAñadirCarrito.setEnabled(true);
            menuItemBuscarCarrito.setEnabled(true);
            menuItemEliminarCarrito.setEnabled(true);

            menuItemCerrarSesion.setEnabled(true);

            menuItemIdiomaEspanol.setEnabled(true);
            menuItemIdiomaIngles.setEnabled(true);
            menuItemIdiomaFrances.setEnabled(true);

        } else {
            menuUsuario.setVisible(false);

            menuProducto.setVisible(true);
            menuCarrito.setVisible(true);
            menuCerrarSesion.setVisible(true);
            menuIdioma.setVisible(true);

            menuProducto.setEnabled(true);
            menuItemCrearProducto.setEnabled(false);
            menuItemEliminarProducto.setEnabled(false);
            menuItemModificarProducto.setEnabled(false);
            menuItemBuscarProducto.setEnabled(true);

            menuCarrito.setEnabled(true);
            menuItemAñadirCarrito.setEnabled(true);
            menuItemBuscarCarrito.setEnabled(true);
            menuItemEliminarCarrito.setEnabled(true);

            menuCerrarSesion.setEnabled(true);
            menuItemCerrarSesion.setEnabled(true);

            menuIdioma.setEnabled(true);
            menuItemIdiomaEspanol.setEnabled(true);
            menuItemIdiomaIngles.setEnabled(true);
            menuItemIdiomaFrances.setEnabled(true);
        }
    }

    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    public void setMenuUsuario(JMenu menuUsuario) {
        this.menuUsuario = menuUsuario;
    }

    public JMenuItem getMenuItemModificarCarrito() {
        return menuItemModificarCarrito;
    }

    public void setMenuItemModificarCarrito(JMenuItem menuItemModificarCarrito) {
        this.menuItemModificarCarrito = menuItemModificarCarrito;
    }

    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    public void setMenuItemEliminarUsuario(JMenuItem menuItemEliminarUsuario) {
        this.menuItemEliminarUsuario = menuItemEliminarUsuario;
    }

    public JMenuItem getMenuItemModificarUsuario() {
        return menuItemModificarUsuario;
    }

    public void setMenuItemModificarUsuario(JMenuItem menuItemModificarUsuario) {
        this.menuItemModificarUsuario = menuItemModificarUsuario;
    }

    public JMenuItem getMenuItemBuscarUsuario() {
        return menuItemBuscarUsuario;
    }

    public void setMenuItemBuscarUsuario(JMenuItem menuItemBuscarUsuario) {
        this.menuItemBuscarUsuario = menuItemBuscarUsuario;
    }

    public JMenuBar getBarraMenu() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getMenuProducto() {
        return menuProducto;
    }

    public void mostrarUsuario(String nombre) {
        lblUsuario.setText("Usuario: " + nombre);
    }

    public void setMenuProducto(JMenu menuProducto) {
        this.menuProducto = menuProducto;
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    public JMenu getMenuCarrito() {
        return menuCarrito;
    }

    public void setMenuCarrito(JMenu menuCarrito) {
        this.menuCarrito = menuCarrito;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public void setMenuItemEliminarProducto(JMenuItem menuItemEliminarProducto) {
        this.menuItemEliminarProducto = menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemModificarProducto() {
        return menuItemModificarProducto;
    }

    public void setMenuItemModificarProducto(JMenuItem menuItemModificarProducto) {
        this.menuItemModificarProducto = menuItemModificarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public void setMenuItemBuscarProducto(JMenuItem menuItemBuscarProducto) {
        this.menuItemBuscarProducto = menuItemBuscarProducto;
    }

    public JMenuItem getMenuItemAñadirCarrito() {
        return menuItemAñadirCarrito;
    }

    public void setMenuItemAñadirCarrito(JMenuItem menuItemAñadirCarrito) {
        this.menuItemAñadirCarrito = menuItemAñadirCarrito;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void setjDesktopPane(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mensajeInternacionalizacionHandler;
    }

    public void setMensajeInternacionalizacionHandler(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
    }

    public JMenu getMenuIdioma() {
        return menuIdioma;
    }

    public void setMenuIdioma(JMenu menuIdioma) {
        this.menuIdioma = menuIdioma;
    }

    public JMenu getMenuCerrarSesion() {
        return menuCerrarSesion;
    }

    public void setMenuCerrarSesion(JMenu menuCerrarSesion) {
        this.menuCerrarSesion = menuCerrarSesion;
    }

    public JMenuItem getMenuItemIdiomaEspanol() {
        return menuItemIdiomaEspanol;
    }

    public void setMenuItemIdiomaEspanol(JMenuItem menuItemIdiomaEspanol) {
        this.menuItemIdiomaEspanol = menuItemIdiomaEspanol;
    }

    public JMenuItem getMenuItemIdiomaIngles() {
        return menuItemIdiomaIngles;
    }

    public void setMenuItemIdiomaIngles(JMenuItem menuItemIdiomaIngles) {
        this.menuItemIdiomaIngles = menuItemIdiomaIngles;
    }

    public JMenuItem getMenuItemIdiomaFrances() {
        return menuItemIdiomaFrances;
    }

    public void setMenuItemIdiomaFrances(JMenuItem menuItemIdiomaFrances) {
        this.menuItemIdiomaFrances = menuItemIdiomaFrances;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public void setMenuItemCerrarSesion(JMenuItem menuItemCerrarSesion) {
        this.menuItemCerrarSesion = menuItemCerrarSesion;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public void setMenuItemEliminarCarrito(JMenuItem menuItemEliminarCarrito) {
        this.menuItemEliminarCarrito = menuItemEliminarCarrito;
    }

    public JMenuItem getMenuItemBuscarCarrito() {
        return menuItemBuscarCarrito;
    }

    public void setMenuItemBuscarCarrito(JMenuItem menuItemBuscarCarrito) {
        this.menuItemBuscarCarrito = menuItemBuscarCarrito;
    }


    private void initComponents() {
        jDesktopPane = new MiJDesktopPane();
        menuBar = new JMenuBar();
        menuProducto = new JMenu(mensajeInternacionalizacionHandler.get("menu.producto"));
        menuCarrito = new JMenu(mensajeInternacionalizacionHandler.get("menu.carrito"));
        menuUsuario = new JMenu(mensajeInternacionalizacionHandler.get("menu.usuario"));
        menuIdioma = new JMenu(mensajeInternacionalizacionHandler.get("menu.idiomas"));
        menuCerrarSesion = new JMenu(mensajeInternacionalizacionHandler.get("menu.cerrarSesion"));

        menuItemCrearProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.crear"));
        menuItemEliminarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.eliminar"));
        menuItemModificarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.actualizar"));
        menuItemBuscarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.buscar"));

        menuItemModificarUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.usuario.modificar"));
        menuItemBuscarUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.usuario.buscar"));
        menuItemEliminarUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.usuario.eliminar"));

        menuItemAñadirCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.añadir"));
        menuItemBuscarCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.buscar"));
        menuItemEliminarCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.eliminar"));

        menuItemIdiomaEspanol = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.idioma.es"));
        menuItemIdiomaIngles = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.idioma.en"));
        menuItemIdiomaFrances = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.idioma.fr"));

        menuItemCerrarSesion = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.salir.cerrar"));

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuIdioma);
        menuBar.add(menuUsuario);
        menuBar.add(menuCerrarSesion);
        menuCerrarSesion.add(menuItemCerrarSesion);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemModificarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemAñadirCarrito);
        menuCarrito.add(menuItemBuscarCarrito);
        menuCarrito.add(menuItemEliminarCarrito);

        menuUsuario.add(menuItemModificarUsuario);
        menuUsuario.add(menuItemBuscarUsuario);
        menuUsuario.add(menuItemEliminarUsuario);

        menuIdioma.add(menuItemIdiomaEspanol);
        menuIdioma.add(menuItemIdiomaIngles);
        menuIdioma.add(menuItemIdiomaFrances);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(mensajeInternacionalizacionHandler.get("app.titulo"));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);

        MiJDesktopPane dibujo = new MiJDesktopPane();
        dibujo.setPreferredSize(new Dimension(400, 300));
        add(dibujo, BorderLayout.CENTER);
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(lblUsuario, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cambiarIdioma(String lenguaje, String pais){
        mensajeInternacionalizacionHandler.setLenguaje(lenguaje,pais);
        setTitle(mensajeInternacionalizacionHandler.get("app.titulo"));

        menuProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto"));
        menuCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito"));
        menuUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario"));
        menuIdioma.setText(mensajeInternacionalizacionHandler.get("menu.idiomas"));
        menuCerrarSesion.setText(mensajeInternacionalizacionHandler.get("menu.cerrarSesion"));

        menuItemCrearProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.crear"));
        menuItemEliminarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.eliminar"));
        menuItemModificarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.actualizar"));
        menuItemBuscarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.buscar"));

        menuItemAñadirCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.añadir"));
        menuItemBuscarCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.buscar"));
        menuItemEliminarCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.eliminar"));

        menuItemModificarUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.modificar"));
        menuItemEliminarUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.eliminar"));
        menuItemBuscarUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.cargar"));

        menuItemIdiomaEspanol.setText(mensajeInternacionalizacionHandler.get("menu.idioma.es"));
        menuItemIdiomaIngles.setText(mensajeInternacionalizacionHandler.get("menu.idioma.en"));
        menuItemIdiomaFrances.setText(mensajeInternacionalizacionHandler.get("menu.idioma.fr"));

        menuItemCerrarSesion.setText(mensajeInternacionalizacionHandler.get("menu.salir.cerrar"));
    }
}
