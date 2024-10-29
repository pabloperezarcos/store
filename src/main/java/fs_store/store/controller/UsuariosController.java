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

    // Endpoint para crear un nuevo usuario
    @PostMapping
    public ResponseEntity<EntityModel<Usuarios>> crearUsuario(@RequestBody Usuarios usuario) {
        Usuarios nuevoUsuario = usuariosService.crearUsuario(usuario);
        return ResponseEntity.created(
                linkTo(methodOn(UsuariosController.class).obtenerUsuarioPorId(nuevoUsuario.getId())).toUri())
                .body(assembler.toModel(nuevoUsuario));
    }

    // Endpoint para actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Usuarios>> actualizarUsuario(@PathVariable int id,
            @RequestBody Usuarios usuarioActualizado) {
        Optional<Usuarios> usuarioOptional = usuariosService.obtenerUsuarioPorId(id);
        if (usuarioOptional.isPresent()) {
            Usuarios usuario = usuariosService.actualizarUsuario(id, usuarioActualizado);
            return ResponseEntity.ok(assembler.toModel(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuariosService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener un usuario por correo electrónico
    @GetMapping("/correo/{correo}")
    public ResponseEntity<EntityModel<Usuarios>> obtenerUsuarioPorCorreo(@PathVariable String correo) {
        Optional<Usuarios> usuario = usuariosService.obtenerUsuarioPorCorreo(correo);
        return usuario.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
