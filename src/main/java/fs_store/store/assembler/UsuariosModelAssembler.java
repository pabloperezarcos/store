package fs_store.store.assembler;

import fs_store.store.controller.UsuariosController;
import fs_store.store.model.Usuarios;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuariosModelAssembler extends RepresentationModelAssemblerSupport<Usuarios, EntityModel<Usuarios>> {

    @SuppressWarnings("unchecked")
    public UsuariosModelAssembler() {
        super(UsuariosController.class, (Class<EntityModel<Usuarios>>) (Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<Usuarios> toModel(Usuarios usuario) {
        // Crea un EntityModel a partir del objeto "Usuarios"
        EntityModel<Usuarios> entityModel = EntityModel.of(usuario);

        // Añade un enlace "self" para obtener detalles del usuario
        entityModel.add(linkTo(methodOn(UsuariosController.class).obtenerUsuarioPorId(usuario.getId())).withSelfRel());

        // Añade un enlace para obtener todos los usuarios
        entityModel.add(linkTo(methodOn(UsuariosController.class).obtenerUsuarios()).withRel("usuarios"));

        return entityModel; // Devuelve el EntityModel con enlaces
    }
}
