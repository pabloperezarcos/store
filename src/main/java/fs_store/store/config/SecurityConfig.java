package fs_store.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Disable CSRF using Lambda DSL
                .csrf(csrf -> csrf.disable())
                // Configure authorization using Lambda DSL
                .authorizeHttpRequests(authz -> authz
                        // Permit access to login and user registration endpoints
                        .requestMatchers("/api/login", "/api/usuarios/**", "/api/productos/**").permitAll()
                        // Require authentication for all other requests
                        .anyRequest().authenticated())
                // Disable session management using Lambda DSL
                .sessionManagement(session -> session.disable());

        return http.build();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configuración de CORS para permitir solicitudes desde localhost:4200
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permitir solicitudes desde este origen
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        // Permitir todos los métodos (GET, POST, etc.)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Permitir encabezados específicos
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        // Permitir credenciales si es necesario
        configuration.setAllowCredentials(true);
        // Registrar la configuración para todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
