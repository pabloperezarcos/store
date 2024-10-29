package fs_store.store.service;

import fs_store.store.model.Productos;
import fs_store.store.repository.ProductosRepository;
import fs_store.store.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;

@SuppressWarnings("unused")
@Service
@Transactional
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public List<Productos> obtenerTodosLosProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Optional<Productos> obtenerProductoPorId(int id) {
        return productosRepository.findById(id);
    }

    @Override
    public Productos crearProducto(Productos producto) {
        return productosRepository.save(producto);
    }

    @Override
    public Productos actualizarProducto(int id, Productos productoActualizado) {
        return productosRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setStock(productoActualizado.getStock());
                    return productosRepository.save(producto);
                })
                .orElseGet(() -> {
                    productoActualizado.setId(id);
                    return productosRepository.save(productoActualizado);
                });
    }

    @Override
    public void eliminarProducto(int id) {
        productosRepository.deleteById(id);
    }

    @Override
    public Optional<Productos> obtenerProductoPorNombre(String nombre) {
        return productosRepository.findByNombre(nombre);
    }
}
