package fs_store.store.service;

import fs_store.store.exception.UsuarioNotFoundException;
import fs_store.store.model.Usuarios;
import fs_store.store.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    // Método para obtener todos los usuarios
    @Override
    public List<Usuarios> obtenerTodosLosUsuarios() {
        return usuariosRepository.findAll();
    }

    // Método para obtener un usuario por su ID
    @Override
    public Optional<Usuarios> obtenerUsuarioPorId(int id) {
        return usuariosRepository.findById(id);
    }

    // Método para crear un nuevo usuario
    @Override
    public Usuarios crearUsuario(Usuarios usuario) {
        // Asignar la contraseña directamente sin encriptarla
        return usuariosRepository.save(usuario);
    }

    // Método para actualizar un usuario existente
    @Override
    public Usuarios actualizarUsuario(int id, Usuarios usuarioActualizado) {
        return usuariosRepository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(usuarioActualizado.getNombre());
                    usuario.setUsername(usuarioActualizado.getUsername());
                    usuario.setEmail(usuarioActualizado.getEmail());
                    usuario.setRol(usuarioActualizado.getRol());
                    usuario.setAddress(usuarioActualizado.getAddress());
                    usuario.setImagen(usuarioActualizado.getImagen());

                    // Solo actualizar la contraseña si se proporciona una nueva
                    if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
                        usuario.setPassword(usuarioActualizado.getPassword());
                    }
                    return usuariosRepository.save(usuario);
                })
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario con ID " + id + " no encontrado"));
    }

    // Método para eliminar un usuario por su ID
    @Override
    public void eliminarUsuario(int id) {
        if (!usuariosRepository.existsById(id)) {
            throw new UsuarioNotFoundException("Usuario con ID " + id + " no encontrado");
        }
        usuariosRepository.deleteById(id);
    }

    // Método para obtener un usuario por su correo electrónico
    @Override
    public Optional<Usuarios> obtenerUsuarioPorEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    // Método para obtener un usuario por su nombre de usuario (username)
    @Override
    public Optional<Usuarios> obtenerUsuarioPorUsername(String username) {
        return usuariosRepository.findByUsername(username);
    }
}
