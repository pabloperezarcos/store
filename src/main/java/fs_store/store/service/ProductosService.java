package fs_store.store.service;

import fs_store.store.model.Productos;
import java.util.List;

public interface ProductosService {

    // Obtener todos los productos
    List<Productos> obtenerTodosLosProductos();

    // Obtener un producto por ID
    Productos obtenerProductoPorId(int id);

    // Crear un nuevo producto
    Productos crearProducto(Productos producto);

    // Actualizar un producto existente
    Productos actualizarProducto(int id, Productos productoActualizado);

    // Eliminar un producto
    void eliminarProducto(int id);

    // Obtener un producto por Nombre
    Productos obtenerProductoPorNombre(String nombre);

    // Obtener un producto por SKU
    Productos obtenerProductoPorSku(String sku);
}
