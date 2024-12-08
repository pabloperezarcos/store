package fs_store.store.exception;

/**
 * Excepción personalizada lanzada cuando un usuario no es encontrado en la base
 * de datos.
 */
public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(String mensaje) {
        super(mensaje);
    }
}
