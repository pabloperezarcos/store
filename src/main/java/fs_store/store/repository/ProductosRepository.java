package fs_store.store.repository;

import fs_store.store.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {

    // Método para buscar productos por SKU
    Optional<Productos> findBySku(String sku);

    // Método para buscar productos por Nombre
    Optional<Productos> findByNombre(String nombre);
}
