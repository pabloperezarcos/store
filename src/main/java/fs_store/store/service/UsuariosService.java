package fs_store.store.service;

import fs_store.store.model.Usuarios;
import java.util.List;
import java.util.Optional;

public interface UsuariosService {

    // Método para obtener todos los usuarios
    List<Usuarios> obtenerTodosLosUsuarios();

    // Método para obtener un usuario por su ID
    Optional<Usuarios> obtenerUsuarioPorId(int id);

    // Método para crear un nuevo usuario
    Usuarios crearUsuario(Usuarios usuario);

    // Método para actualizar un usuario existente
    Usuarios actualizarUsuario(int id, Usuarios usuarioActualizado);

    // Método para eliminar un usuario por su ID
    void eliminarUsuario(int id);

    // Método para obtener un usuario por su correo electrónico
    Optional<Usuarios> obtenerUsuarioPorEmail(String email);

    // Método para obtener un usuario por su nombre de usuario (username)
    Optional<Usuarios> obtenerUsuarioPorUsername(String username);
}
