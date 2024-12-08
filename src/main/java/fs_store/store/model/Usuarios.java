package fs_store.store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

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
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    // Nombre de usuario (username)
    @NotBlank(message = "El username es obligatorio")
    @Size(max = 50, message = "El username no debe exceder los 50 caracteres")
    @Column(name = "username", unique = true)
    private String username;

    // Correo electrónico del usuario
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Column(name = "email", unique = true)
    private String email;

    // Contraseña del usuario
    @NotBlank(message = "La contraseña es obligatoria")
    @JsonIgnore // Oculta este campo en las respuestas JSON
    @Column(name = "password")
    private String password;

    // Fecha de nacimiento del usuario
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    // Dirección de despacho del usuario
    @Size(max = 255, message = "La dirección no debe exceder los 255 caracteres")
    @Column(name = "address")
    private String address;

    // Rol del usuario (Administrador, Cliente, etc.)
    @NotBlank(message = "El rol es obligatorio")
    @Column(name = "rol")
    private String rol;

    // Imagen de perfil del usuario
    @Size(max = 255, message = "La URL de la imagen no debe exceder los 255 caracteres")
    @Column(name = "imagen")
    private String imagen;

    // Constructor por defecto
    public Usuarios() {
    }

    // Constructor con parámetros
    public Usuarios(String nombre, String username, String email, String password, Date birthdate, String address,
            String rol, String imagen) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
        this.rol = rol;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
