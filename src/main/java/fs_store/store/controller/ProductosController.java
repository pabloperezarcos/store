package fs_store.store.controller;

import fs_store.store.assembler.ProductosModelAssembler;
import fs_store.store.model.Productos;
import fs_store.store.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private ProductosModelAssembler assembler;

    // Obtener todos los productos
    @GetMapping
    public CollectionModel<EntityModel<Productos>> obtenerTodosLosProductos() {
        List<EntityModel<Productos>> productos = productosService.obtenerTodosLosProductos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductosController.class).obtenerTodosLosProductos()).withSelfRel());
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public EntityModel<Productos> obtenerProductoPorId(@PathVariable(value = "id") int id) {
        Productos producto = productosService.obtenerProductoPorId(id);
        return assembler.toModel(producto);
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<EntityModel<Productos>> crearProducto(@Valid @RequestBody Productos producto) {
        Productos nuevoProducto = productosService.crearProducto(producto);
        EntityModel<Productos> entityModel = assembler.toModel(nuevoProducto);

        return ResponseEntity
                .created(
                        linkTo(methodOn(ProductosController.class).obtenerProductoPorId(nuevoProducto.getId())).toUri())
                .body(entityModel);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Productos>> actualizarProducto(@PathVariable(value = "id") int id,
            @Valid @RequestBody Productos productoDetalles) {
        Productos actualizadoProducto = productosService.actualizarProducto(id, productoDetalles);
        EntityModel<Productos> entityModel = assembler.toModel(actualizadoProducto);

        return ResponseEntity.ok().body(entityModel);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable(value = "id") int id) {
        productosService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
