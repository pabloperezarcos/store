package fs_store.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF para simplificar
                .csrf().disable()
                // Configurar autorización de solicitudes
                .authorizeHttpRequests(authorize -> authorize
                        // Permitir acceso público al endpoint de login y registro de usuarios
                        .requestMatchers("/api/login", "/api/usuarios/**").permitAll()
                        // Proteger todos los demás endpoints
                        .anyRequest().authenticated())
                // Deshabilitar la gestión de sesiones
                .sessionManagement()
                .disable();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
