package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarritoDAOMemoria implements CarritoDAO {

    private List<Carrito> carritos;
    private int codigoCarrito = 1;

    public CarritoDAOMemoria() {
        this.carritos = new ArrayList<Carrito>();
    }

    @Override
    public void crear(Carrito carrito) {
        carrito.setCodigo(codigoCarrito++);
        carritos.add(carrito);
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo) {
                return carrito;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        Iterator<Carrito> iterator = carritos.iterator();
        while (iterator.hasNext()) {
            Carrito carrito = iterator.next();
            if (carrito.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }

    @Override
    public List<Carrito> listarPorUsuario(String username) {
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario() != null && carrito.getUsuario().getUsername().equals(username)) {
                resultado.add(carrito);
            }
        }
        return resultado;
    }
}
