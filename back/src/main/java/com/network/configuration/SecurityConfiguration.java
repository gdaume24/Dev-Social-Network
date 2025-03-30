package com.network.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
        JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(cors -> cors.configurationSource(request -> {
            var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
            corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200")); // Autorise l'origine Angular
            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Méthodes autorisées
            corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // En-têtes autorisés
            corsConfiguration.setExposedHeaders(List.of("Authorization")); // En-têtes exposés
            return corsConfiguration;
        }))
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
                req->req.requestMatchers("/auth/login", "/auth/register", "/error", "/swagger-ui/**", "v3/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        )
        .sessionManagement(session->session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
}

