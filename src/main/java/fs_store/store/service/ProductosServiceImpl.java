package fs_store.store.service;

import fs_store.store.exception.ResourceNotFoundException;
import fs_store.store.model.Productos;
import fs_store.store.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    // Obtener todos los productos
    @Override
    public List<Productos> obtenerTodosLosProductos() {
        return productosRepository.findAll();
    }

    // Obtener un producto por ID
    @Override
    public Productos obtenerProductoPorId(int id) {
        return productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
    }

    // Crear un nuevo producto
    @Override
    public Productos crearProducto(Productos producto) {
        // Validar que el SKU sea único
        if (productosRepository.findBySku(producto.getSku()).isPresent()) {
            throw new IllegalArgumentException("El SKU ya está en uso.");
        }
        return productosRepository.save(producto);
    }

    // Actualizar un producto existente
    @Override
    public Productos actualizarProducto(int id, Productos productoActualizado) {
        Productos producto = productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));

        // Validar que el nuevo SKU (si cambia) sea único
        if (!producto.getSku().equals(productoActualizado.getSku())) {
            productosRepository.findBySku(productoActualizado.getSku())
                    .ifPresent(existingProducto -> {
                        throw new IllegalArgumentException("El SKU ya está en uso.");
                    });
        }

        producto.setNombre(productoActualizado.getNombre());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
        producto.setImagen(productoActualizado.getImagen());
        producto.setSku(productoActualizado.getSku());

        return productosRepository.save(producto);
    }

    // Eliminar un producto
    @Override
    public void eliminarProducto(int id) {
        Productos producto = productosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        productosRepository.delete(producto);
    }

    // Obtener un producto por Nombre
    @Override
    public Productos obtenerProductoPorNombre(String nombre) {
        return productosRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "nombre", nombre));
    }

    // Obtener un producto por SKU
    @Override
    public Productos obtenerProductoPorSku(String sku) {
        return productosRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "sku", sku));
    }
}
