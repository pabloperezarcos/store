package fs_store.store.assembler;

import fs_store.store.controller.UsuariosController;
import fs_store.store.model.Usuarios;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuariosModelAssembler implements RepresentationModelAssembler<Usuarios, EntityModel<Usuarios>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Usuarios> toModel(Usuarios usuario) {
        // Asegurarse de que el usuario no es nulo
        if (usuario == null) {
            throw new IllegalArgumentException("El objeto usuario no puede ser nulo");
        }

        EntityModel<Usuarios> entityModel = EntityModel.of(usuario,
                // Enlace self para obtener detalles del usuario
                linkTo(methodOn(UsuariosController.class).obtenerUsuarioPorId(usuario.getId())).withSelfRel(),
                // Enlace para obtener todos los usuarios
                linkTo(methodOn(UsuariosController.class).obtenerUsuarios()).withRel("usuarios"));

        // Añadir enlace para obtener el usuario por email si no es nulo
        if (usuario.getEmail() != null && !usuario.getEmail().isEmpty()) {
            entityModel.add(linkTo(methodOn(UsuariosController.class).obtenerUsuarioPorEmail(usuario.getEmail()))
                    .withRel("email"));
        }

        // Añadir enlace para obtener el usuario por username si no es nulo
        if (usuario.getUsername() != null && !usuario.getUsername().isEmpty()) {
            entityModel.add(linkTo(methodOn(UsuariosController.class).obtenerUsuarioPorUsername(usuario.getUsername()))
                    .withRel("username"));
        }

        // Enlace para actualizar el usuario
        entityModel.add(linkTo(methodOn(UsuariosController.class).actualizarUsuario(usuario.getId(), usuario))
                .withRel("actualizar"));

        // Enlace para eliminar el usuario
        entityModel
                .add(linkTo(methodOn(UsuariosController.class).eliminarUsuario(usuario.getId())).withRel("eliminar"));

        return entityModel;
    }
}
