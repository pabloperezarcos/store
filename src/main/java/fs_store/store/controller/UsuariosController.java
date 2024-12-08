package fs_store.store.controller;

import fs_store.store.assembler.UsuariosModelAssembler;
import fs_store.store.exception.UsuarioNotFoundException;
import fs_store.store.model.Usuarios;
import fs_store.store.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

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
    public EntityModel<Usuarios> obtenerUsuarioPorId(@PathVariable int id) {
        Usuarios usuario = usuariosService.obtenerUsuarioPorId(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario con ID " + id + " no encontrado"));

        return assembler.toModel(usuario);
    }

    // Endpoint para crear un nuevo usuario
    @PostMapping
    public ResponseEntity<EntityModel<Usuarios>> crearUsuario(@Valid @RequestBody Usuarios usuario) {
        Usuarios nuevoUsuario = usuariosService.crearUsuario(usuario);

        return ResponseEntity
                .created(linkTo(methodOn(UsuariosController.class).obtenerUsuarioPorId(nuevoUsuario.getId())).toUri())
                .body(assembler.toModel(nuevoUsuario));
    }

    // Endpoint para actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Usuarios>> actualizarUsuario(@PathVariable int id,
            @Valid @RequestBody Usuarios usuarioActualizado) {
        Usuarios usuario = usuariosService.actualizarUsuario(id, usuarioActualizado);

        return ResponseEntity.ok(assembler.toModel(usuario));
    }

    // Endpoint para eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuariosService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener un usuario por correo electrónico
    @GetMapping("/email/{email}")
    public EntityModel<Usuarios> obtenerUsuarioPorEmail(@PathVariable String email) {
        Usuarios usuario = usuariosService.obtenerUsuarioPorEmail(email)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario con email " + email + " no encontrado"));

        return assembler.toModel(usuario);
    }

    // Endpoint para obtener un usuario por nombre de usuario (username)
    @GetMapping("/username/{username}")
    public EntityModel<Usuarios> obtenerUsuarioPorUsername(@PathVariable String username) {
        Usuarios usuario = usuariosService.obtenerUsuarioPorUsername(username)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario con username " + username + " no encontrado"));

        return assembler.toModel(usuario);
    }
}
