package fs_store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fs_store.store.model.Usuarios;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    // Buscar usuario por correo electrónico
    Optional<Usuarios> findByEmail(String email);

    // Buscar usuario por nombre de usuario (username)
    Optional<Usuarios> findByUsername(String username);
}