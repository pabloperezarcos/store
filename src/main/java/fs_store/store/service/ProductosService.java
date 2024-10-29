package fs_store.store.service;

import fs_store.store.model.Productos;
import java.util.List;
import java.util.Optional;

public interface ProductosService {

    List<Productos> obtenerTodosLosProductos();

    Optional<Productos> obtenerProductoPorId(int id);

    Productos crearProducto(Productos producto);

    Productos actualizarProducto(int id, Productos productoActualizado);

    void eliminarProducto(int id);

    Optional<Productos> obtenerProductoPorNombre(String nombre);
}
