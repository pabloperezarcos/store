package fs_store.store.controller;

import fs_store.store.assembler.UsuariosModelAssembler;
import fs_store.store.model.Usuarios;
import fs_store.store.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private UsuariosModelAssembler assembler;

    // Endpoint para obtener todos los usuarios
    @GetMapping
    public CollectionModel<EntityModel<Usuarios>> obtenerUsuarios() {
        List<EntityModel<Usuarios>> usuarios = usuariosService.obtenerTodosLosUsuarios().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuariosController.class).obtenerUsuarios()).withSelfRel());
    }

    // Endpoint para obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuarios>> obtenerUsuarioPorId(@PathVariable int id) {
        Optional<Usuarios> usuario = usuariosService.obtenerUsuarioPorId(id);
        return usuario.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Otros métodos (crear, actualizar, eliminar) permanecen iguales
}
