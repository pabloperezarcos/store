package fs_store.store.controller;

import fs_store.store.assembler.ProductosModelAssembler;
import fs_store.store.model.Productos;
import fs_store.store.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private ProductosModelAssembler assembler;

    // Endpoint para obtener todos los productos
    @GetMapping
    public CollectionModel<EntityModel<Productos>> obtenerProductos() {
        List<EntityModel<Productos>> productos = productosService.obtenerTodosLosProductos().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductosController.class).obtenerProductos()).withSelfRel());
    }

    // Endpoint para obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Productos>> obtenerProductoPorId(@PathVariable int id) {
        Optional<Productos> producto = productosService.obtenerProductoPorId(id);
        return producto.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para crear un nuevo producto
    @PostMapping
    public ResponseEntity<EntityModel<Productos>> crearProducto(@RequestBody Productos producto) {
        Productos nuevoProducto = productosService.crearProducto(producto);
        return ResponseEntity
                .created(
                        linkTo(methodOn(ProductosController.class).obtenerProductoPorId(nuevoProducto.getId())).toUri())
                .body(assembler.toModel(nuevoProducto));
    }

    // Endpoint para actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Productos>> actualizarProducto(@PathVariable int id,
            @RequestBody Productos productoActualizado) {
        Optional<Productos> productoOptional = productosService.obtenerProductoPorId(id);
        if (productoOptional.isPresent()) {
            Productos producto = productosService.actualizarProducto(id, productoActualizado);
            return ResponseEntity.ok(assembler.toModel(producto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        productosService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener un producto por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EntityModel<Productos>> obtenerProductoPorNombre(@PathVariable String nombre) {
        Optional<Productos> producto = productosService.obtenerProductoPorNombre(nombre);
        return producto.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
