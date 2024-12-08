package fs_store.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

// Clase que representa la entidad Productos en la base de datos
@Entity
@Table(name = "PRODUCTOS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "SKU", name = "UK_PRODUCTOS_SKU")
})
public class Productos {

    // Identificador único de producto
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productos_seq_gen")
    @SequenceGenerator(name = "productos_seq_gen", sequenceName = "PRODUCTOS_SEQ", allocationSize = 1)
    private int id;

    // Nombre del producto
    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;

    // Descripción del producto
    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;

    // Precio del producto
    @Positive(message = "El precio debe ser positivo")
    @Column(name = "PRECIO", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;

    // Cantidad disponible en stock
    @Positive(message = "La cantidad en stock debe ser positiva")
    @Column(name = "STOCK", nullable = false)
    private int stock;

    // Ruta de la imagen del producto
    @Column(name = "IMAGEN", length = 255)
    private String imagen;

    // SKU único del producto
    @NotBlank(message = "El SKU es obligatorio")
    @Column(name = "SKU", nullable = false, length = 50, unique = true)
    private String sku;

    // Constructor por defecto
    public Productos() {
    }

    // Constructor con parámetros (excepto el ID, que es generado)
    public Productos(String nombre, String descripcion, BigDecimal precio, int stock, String imagen, String sku) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.sku = sku;
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

    public String getImagen() {
        return imagen;
    }

    public String getSku() {
        return sku;
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

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
