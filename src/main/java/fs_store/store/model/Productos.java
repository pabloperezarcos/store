package fs_store.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

// Clase que representa la entidad Productos en la base de datos
@Entity
@Table(name = "productos")
public class Productos {

    // Identificador único de producto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Nombre del producto
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    // Descripción del producto
    private String descripcion;

    // Precio del producto
    @Positive(message = "El precio debe ser positivo")
    private BigDecimal precio;

    // Cantidad disponible en stock
    @Positive(message = "La cantidad en stock debe ser positiva")
    private int stock;

    // Constructor por defecto
    public Productos() {
    }

    // Constructor con parámetros
    public Productos(int id, String nombre, String descripcion, BigDecimal precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y setters
    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
