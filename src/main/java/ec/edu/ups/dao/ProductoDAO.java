package ec.edu.ups.dao;
import ec.edu.ups.modelo.Producto;
import java.util.List;

/**
 * Interfaz DAO para la gestión de productos.
 * Define las operaciones básicas y métodos de búsqueda sobre objetos de tipo Producto.
 */
public interface ProductoDAO {

    /**
     * Crea un nuevo producto en el sistema
     * @param producto Objeto Producto a registar
     */
    void crear(Producto producto);

    /**
     * Busca un producto por su código único.
     * @param codigo Código del producto
     * @return Producto encontrado o null si no existe
     */
    Producto buscarPorCodigo(int codigo);

    /**
     * Busca una lista de productos que coincidan con el nombre dado.
     * @param nombre Nombre dle producto o parte del nombre
     * @return Lista de productos que coinciden con el criterio
     */
    List<Producto> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un producto existente.
     * @param producto Producto con información actualizada
     */
    void actualizar(Producto producto);

    /**
     * Elimina un producto del sistema mediante su código.
     * @param codigo Código del producto a eliminar
     */
    void eliminar(int codigo);

    /**
     * Modifica un producto si existe en el sistema.
     * Puede usarse como alternativa a 'actualizar'
     * @param producto Producto con datos modificados
     * @return true si la modificación fue exitosa o false si no se encontró el producto
     */
    boolean modificar(Producto producto);

    /**
     * Lista todos los productos registrados en el sistema.
     * @return Lista completa de productos
     */
    List<Producto> listarTodos();

}