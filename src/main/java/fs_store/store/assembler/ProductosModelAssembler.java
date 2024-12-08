package fs_store.store.assembler;

import fs_store.store.controller.ProductosController;
import fs_store.store.model.Productos;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductosModelAssembler implements RepresentationModelAssembler<Productos, EntityModel<Productos>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Productos> toModel(@NonNull Productos producto) {
        return EntityModel.of(producto,
                // Enlace a sí mismo
                linkTo(methodOn(ProductosController.class).obtenerProductoPorId(producto.getId())).withSelfRel(),
                // Enlace a la colección de productos
                linkTo(methodOn(ProductosController.class).obtenerTodosLosProductos()).withRel("productos"),
                // Enlace para actualizar el producto
                linkTo(methodOn(ProductosController.class).actualizarProducto(producto.getId(), producto)).withRel("actualizar"),
                // Enlace para eliminar el producto
                linkTo(methodOn(ProductosController.class).eliminarProducto(producto.getId())).withRel("eliminar")
        );
    }
}
