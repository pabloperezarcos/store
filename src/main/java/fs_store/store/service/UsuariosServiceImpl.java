package fs_store.store.service;

import fs_store.store.model.Usuarios;
import fs_store.store.repository.UsuariosRepository;
import fs_store.store.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.transaction.Transactional;

@SuppressWarnings("unused")
@Service
@Transactional
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);
        return usuariosRepository.save(usuario);
    }

    // Método para actualizar un usuario existente
    @Override
    public Usuarios actualizarUsuario(int id, Usuarios usuarioActualizado) {
        return usuariosRepository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(usuarioActualizado.getNombre());
                    usuario.setCorreo(usuarioActualizado.getCorreo());
                    usuario.setRol(usuarioActualizado.getRol());
                    usuario.setDireccionDespacho(usuarioActualizado.getDireccionDespacho());
                    if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
                        String encodedPassword = passwordEncoder.encode(usuarioActualizado.getPassword());
                        usuario.setPassword(encodedPassword);
                    }
                    return usuariosRepository.save(usuario);
                })
                .orElseGet(() -> {
                    usuarioActualizado.setId(id);
                    String encodedPassword = passwordEncoder.encode(usuarioActualizado.getPassword());
                    usuarioActualizado.setPassword(encodedPassword);
                    return usuariosRepository.save(usuarioActualizado);
                });
    }

    // Método para eliminar un usuario por su ID
    @Override
    public void eliminarUsuario(int id) {
        usuariosRepository.deleteById(id);
    }

    // Método para obtener un usuario por su correo electrónico
    @Override
    public Optional<Usuarios> obtenerUsuarioPorCorreo(String correo) {
        return usuariosRepository.findByCorreo(correo);
    }
}
