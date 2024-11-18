# Store Application

Este proyecto es una aplicación de gestión de productos y usuarios desarrollada en **Spring Boot** utilizando una arquitectura basada en capas. La aplicación incluye soporte para operaciones CRUD, validaciones, manejo de enlaces HATEOAS y conexión con una base de datos mediante JPA.

---

## Estructura del Proyecto

El proyecto sigue una arquitectura modular organizada en capas:

- **Assembler**: Contiene ensambladores para implementar HATEOAS, añadiendo enlaces a las respuestas de las APIs.
- **Controller**: Define los endpoints RESTful para productos y usuarios.
- **Model**: Define las entidades `Productos` y `Usuarios` mapeadas a tablas de base de datos.
- **Repository**: Define los repositorios para interactuar con la base de datos.
- **Service**: Implementa la lógica de negocio para productos y usuarios.
- **Resources**: Contiene la configuración de la aplicación (`application.properties`).

---

## Arquetipo del Proyecto

El proyecto ha sido diseñado siguiendo un **arquetipo basado en la arquitectura en capas**, lo que permite una separación clara de responsabilidades. Esto asegura que el código sea más mantenible, reutilizable y alineado con las mejores prácticas de desarrollo en Spring Boot. Las principales características del arquetipo incluyen:

1. **Separación de capas**:
   - **Controladores** (`Controller`) para manejar las solicitudes HTTP.
   - **Servicios** (`Service`) para la lógica de negocio.
   - **Repositorios** (`Repository`) para interactuar con la base de datos.
   - **Modelos** (`Model`) para representar las entidades.

2. **Implementación de HATEOAS**:
   - Uso de ensambladores (`ModelAssembler`) para incluir enlaces en las respuestas RESTful, facilitando la navegabilidad entre recursos.

3. **Validaciones**:
   - Uso de la API de validación de Jakarta (`@NotBlank`, `@Positive`, `@Email`) para garantizar la integridad de los datos.

4. **Transacciones**:
   - Gestión automática de transacciones mediante la anotación `@Transactional` en la capa de servicios.

Este arquetipo permite implementar fácilmente nuevas funcionalidades manteniendo la cohesión y separación de responsabilidades.

---

## Tecnologías y Dependencias

Este proyecto utiliza las siguientes tecnologías y dependencias:

- **Spring Boot 3.3.5**: Framework principal para la creación de aplicaciones.
- **Spring Boot Starter Data JPA**: Gestión de datos y ORM con Hibernate.
- **Spring Boot Starter HATEOAS**: Implementación de hipermedios para respuestas RESTful.
- **Jakarta Persistence API**: Definición de entidades y validaciones.
- **Jakarta Validation API**: Validaciones para datos de entrada.
- **Oracle JDBC Driver (ojdbc11)**: Conexión a base de datos Oracle.
- **Lombok**: Para reducir el código boilerplate (opcional).
- **Spring Boot DevTools**: Herramientas de desarrollo en tiempo de ejecución.
- **Spring Boot Actuator**: Monitoreo y métricas del sistema.

---

## Endpoints Disponibles

### Productos

    GET /api/productos
    Retorna todos los productos.

    GET /api/productos/{id}
    Retorna un producto por su ID.

    POST /api/productos
    Crea un nuevo producto.

    PUT /api/productos/{id}
    Actualiza un producto existente.

    DELETE /api/productos/{id}
    Elimina un producto.

    GET /api/productos/nombre/{nombre}
    Retorna un producto por su nombre.

### Usuarios

    GET /api/usuarios
    Retorna todos los usuarios.

    GET /api/usuarios/{id}
    Retorna un usuario por su ID.

    POST /api/usuarios
    Crea un nuevo usuario.

    PUT /api/usuarios/{id}
    Actualiza un usuario existente.

    DELETE /api/usuarios/{id}
    Elimina un usuario.

    GET /api/usuarios/correo/{correo}
    Retorna un usuario por su correo electrónico.

## Características Clave

    Validaciones:
        Los modelos Productos y Usuarios incluyen validaciones usando anotaciones como @NotBlank, @Positive, y @Email.

    HATEOAS:
        Las respuestas incluyen enlaces a otros recursos relacionados usando ensambladores (ProductosModelAssembler y UsuariosModelAssembler).

    Transacciones:
        La lógica de negocio en Service está envuelta en transacciones gracias a la anotación @Transactional.
