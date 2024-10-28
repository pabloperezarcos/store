package fs_store.store.assembler;

import fs_store.store.controller.ProductosController;
import fs_store.store.model.Productos;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductosModelAssembler extends RepresentationModelAssemblerSupport<Productos, EntityModel<Productos>> {

    @SuppressWarnings("unchecked")
    public ProductosModelAssembler() {
        super(ProductosController.class, (Class<EntityModel<Productos>>) (Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<Productos> toModel(Productos producto) {
        EntityModel<Productos> entityModel = EntityModel.of(producto);

        entityModel
                .add(linkTo(methodOn(ProductosController.class).obtenerProductoPorId(producto.getId())).withSelfRel());
        entityModel.add(linkTo(methodOn(ProductosController.class).obtenerProductos()).withRel("productos"));

        return entityModel;
    }
}
