package fs_store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fs_store.store.model.Productos;
import java.util.Optional;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {
    // Método para buscar un producto por su nombre
    Optional<Productos> findByNombre(String nombre);
}
