package com.eep.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig   {
	 private final UserDetailsService userDetailsService;
	 

	    public SecurityConfig(UserDetailsService userDetailsService) {
	        this.userDetailsService = userDetailsService;
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	        // Configurar el AuthenticationManager directamente
	        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
	        return authenticationManager;
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	            .csrf().disable()  // Desactiva CSRF (evita el 403 por no tener un token CSRF)
	            .authorizeHttpRequests(authz -> authz
	                .requestMatchers(HttpMethod.GET, "/login").permitAll()
	                .requestMatchers(HttpMethod.GET, "/registro").permitAll()
	                .requestMatchers(HttpMethod.POST, "/registro").permitAll()
	                .requestMatchers(HttpMethod.POST, "/buscar").permitAll()
	                .requestMatchers("/css/**", "/img/**", "/js/**").permitAll()
	                .requestMatchers(HttpMethod.GET, "/misfavoritas").permitAll()
	                .requestMatchers(HttpMethod.POST, "/misfavoritas").permitAll()
	                .requestMatchers(HttpMethod.POST, "/favoritas/eliminar/**").permitAll()
	                .requestMatchers(HttpMethod.GET, "/perfil").permitAll()
	                .requestMatchers(HttpMethod.POST, "/perfil").permitAll()
	                .requestMatchers(HttpMethod.POST, "/favoritas/guardar").authenticated()  // Asegura que esta ruta solo es accesible si el usuario está autenticado
	                .requestMatchers("/inicio").authenticated()  // Solo autenticados
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	                .loginPage("/login")  // Página de login personalizada
	                .failureUrl("/login?error=true") 
	                .defaultSuccessUrl("/inicio", true)  // Redirige a /inicio después del login exitoso
	            )
	            .sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))  // Crear sesión solo si es necesario
	            .userDetailsService(userDetailsService)  // Usamos el UserDetailsService para la autenticación
	            .build();
	    }
	    

}
