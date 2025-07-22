package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CarritoDAOTexto implements CarritoDAO {

    private final String rutaCarpeta;
    private final List<Carrito> carritos = new ArrayList<>();
    private final UsuarioDAO usuarioDAO;
    private final ProductoDAO productoDAO;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CarritoDAOTexto(String rutaCarpeta, UsuarioDAO usuarioDAO, ProductoDAO productoDAO) {
        this.rutaCarpeta = rutaCarpeta;
        this.usuarioDAO = usuarioDAO;
        this.productoDAO = productoDAO;

        File carpeta = new File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        cargarCarritos();
    }

    private void cargarCarritos() {
        carritos.clear();
        File archivoCarritos = new File(rutaCarpeta, rutaCarpeta);
        if (!archivoCarritos.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCarritos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    Carrito c = new Carrito(null);
                    c.setCodigo(Integer.parseInt(partes[0]));

                    try {
                        Date fecha = sdf.parse(partes[1]);
                        c.setFechaCreacion(fecha);
                    } catch (Exception e) {
                        c.setFechaCreacion(new Date());
                    }

                    String cedula = partes[2];
                    Usuario usuario = usuarioDAO.buscarPorUsername(cedula);

                    if (usuario != null) {
                        c.setUsuario(usuario);
                        carritos.add(c);
                    } else {
                        System.out.println("Usuario no encontrado para cedula: " + cedula);
                    }
                }
            }
            cargarItemsCarrito();
        } catch (IOException e) {
            System.out.println("Error cargando carritos: " + e.getMessage());
        }
    }

    private void cargarItemsCarrito() {
        File archivoItems = new File(rutaCarpeta, "items_carrito.txt");
        if (!archivoItems.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoItems))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    int carritoId = Integer.parseInt(partes[0]);
                    int codigoProducto = Integer.parseInt(partes[1]);
                    int cantidad = Integer.parseInt(partes[2]);

                    Carrito carrito = carritos.stream()
                            .filter(c -> c.getCodigo() == carritoId)
                            .findFirst()
                            .orElse(null);

                    Producto producto = productoDAO.buscarPorCodigo(codigoProducto);

                    if (carrito != null && producto != null) {
                        carrito.agregarProducto(producto, cantidad);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error cargando items de carritos: " + e.getMessage());
        }
    }

    private void guardarCarritos() {
        File archivoCarritos = new File(rutaCarpeta, "carritos.txt");
        File archivoItems = new File(rutaCarpeta, "items_carrito.txt");

        try (PrintWriter pwCarritos = new PrintWriter(new FileWriter(archivoCarritos));
             PrintWriter pwItems = new PrintWriter(new FileWriter(archivoItems))) {

            for (Carrito c : carritos) {
                String fechaStr = sdf.format(c.getFechaCreacion());
                pwCarritos.println(c.getCodigo() + "," + fechaStr + "," +
                        (c.getUsuario() != null ? c.getUsuario().getUsername() : ""));

                for (ItemCarrito item : c.obtenerItems()) {
                    pwItems.println(c.getCodigo() + "," +
                            item.getProducto().getCodigo() + "," +
                            item.getCantidad());
                }
            }
        } catch (IOException e) {
            System.out.println("Error guardando carritos o items: " + e.getMessage());
        }
    }

    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
        guardarCarritos();
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        return carritos.stream()
                .filter(c -> c.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                guardarCarritos();
                return;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos();
    }

    @Override
    public List<Carrito> listarTodos() {
        return new ArrayList<>(carritos);
    }

    @Override
    public List<Carrito> listarPorUsuario(String username) {
        List<Carrito> encontrados = new ArrayList<>();
        for (Carrito c : carritos) {
            if (c.getUsuario() != null && c.getUsuario().getUsername().equals(username)) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }
}
