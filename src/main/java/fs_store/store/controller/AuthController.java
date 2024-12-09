package fs_store.store.controller;

import fs_store.store.dto.LoginRequest;
import fs_store.store.model.Usuarios;
import fs_store.store.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuariosService usuariosService;

    /**
     * Endpoint para manejar el login de usuarios.
     * 
     * @param loginRequest Contiene el nombre de usuario y la contraseña.
     * @return Respuesta indicando éxito o fracaso.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autenticar las credenciales
            @SuppressWarnings("unused")
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            // Obtener usuario desde el servicio por username
            Usuarios usuario = usuariosService.obtenerUsuarioPorUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Construir la respuesta con el rol del usuario
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login exitoso");
            response.put("user", Map.of(
                    "username", usuario.getUsername(),
                    "rol", usuario.getRol()));

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
