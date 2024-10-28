package fs_store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fs_store.store.model.Usuarios;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    // Método para buscar un usuario por su correo electrónico
    Optional<Usuarios> findByCorreo(String correo);
}