package fs_store.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

// Clase que representa la entidad Usuarios en la base de datos
@Entity
@Table(name = "usuarios")
public class Usuarios {

    // Identificador único de usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Nombre del usuario
    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre")
    private String nombre;

    // Correo electrónico del usuario
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Column(name = "correo", unique = true)
    private String correo;

    // Rol del usuario (Administrador, Cliente, etc.)
    @NotBlank(message = "El rol es obligatorio")
    @Column(name = "rol")
    private String rol;

    // Dirección de despacho del usuario
    @Column(name = "direccion_despacho")
    private String direccionDespacho;

    // Contraseña del usuario
    @NotBlank(message = "La contraseña es obligatoria")
    @JsonIgnore // Oculta este campo en las respuestas JSON
    @Column(name = "password")
    private String password;

    // Constructor por defecto
    public Usuarios() {
    }

    // Constructor con parámetros
    public Usuarios(int id, String nombre, String correo, String rol, String direccionDespacho, String password) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.direccionDespacho = direccionDespacho;
        this.password = password;
    }

    // Getters y setters
    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

    public String getDireccionDespacho() {
        return direccionDespacho;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setDireccionDespacho(String direccionDespacho) {
        this.direccionDespacho = direccionDespacho;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
