package com.academia.plataforma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
/*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; 
*/
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.academia.plataforma.security.filter.JwtAuthenticationFilter;
import com.academia.plataforma.security.filter.JwtValidationFilter;


@Configuration
public class SpringSecurityConfig 
{
	
	//en esta clase se configura la seguridad de Spring Boot, como autenticación, autorización por roles, etc.
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	
	@Bean
	AuthenticationManager authenticationManager() throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	//con este metodo creamos nuestro filtrado por roles y rutas
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{	
      
	    return http.authorizeHttpRequests(auth -> auth
	    		.requestMatchers("/api/auth/login").permitAll() // ← permite el login sin token
	            .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll() // ← permite el registro sin token
	            .requestMatchers("/api/auth/me").permitAll() // ← permite el acceso al endpoint /me sin token
	            
	            // Permisos de acceso para administradores
	            .requestMatchers(HttpMethod.GET, "/api/usuarios").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/usuarios/{id}").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.PUT, "/api/usuarios/{id}").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.DELETE, "/api/usuarios/{id}").hasRole("ADMIN")
	            
	            .requestMatchers(HttpMethod.GET, "/api/profesores").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/profesores/{id}").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.POST, "/api/profesores").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/profesores/{id}/asignaturas").hasRole("ADMIN")
	            
	            .requestMatchers(HttpMethod.GET, "/api/estudiantes").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/estudiantes/{id}").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.POST, "/api/estudiantes").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/estudiantes/{id}/notas").hasRole("ADMIN")
	            
	            .requestMatchers(HttpMethod.POST, "/api/cursos").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/cursos").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/cursos/{id}").hasRole("ADMIN")
	            
	            .requestMatchers(HttpMethod.POST, "/api/asignaturas").hasAnyRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/asignaturas").hasAnyRole("ADMIN", "ESTUDIANTE", "PROFESOR")
	            .requestMatchers(HttpMethod.GET, "/api/asignaturas/{id}").hasAnyRole("ADMIN", "PROFESOR")
	            .requestMatchers(HttpMethod.PUT, "/api/asignaturas/{id}").hasAnyRole("ADMIN", "PROFESOR")
	            .requestMatchers(HttpMethod.DELETE, "/api/asignaturas/{id}").hasAnyRole("ADMIN", "PROFESOR")
	            
	            .requestMatchers(HttpMethod.GET, "/api/periodos").hasAnyRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/periodos/{id}").hasAnyRole("ADMIN")
	            .requestMatchers(HttpMethod.POST, "/api/periodos").hasAnyRole("ADMIN")
	            
	            .requestMatchers(HttpMethod.GET, "/api/notas/asignatura/{id}").hasAnyRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/notas/estudiante/{id}").hasAnyRole("ADMIN", "ESTUDIANTE")
	            .requestMatchers(HttpMethod.POST, "/api/notas").hasAnyRole("ADMIN", "PROFESOR")
	            .requestMatchers(HttpMethod.PUT, "/api/notas/{id}").hasAnyRole("ADMIN")
	            .requestMatchers(HttpMethod.DELETE, "/api/notas/{noteId}").hasAnyRole("ADMIN")
	            
	            .requestMatchers(HttpMethod.POST, "/api/materiales").hasAnyRole("ADMIN", "PROFESOR")
	            .requestMatchers(HttpMethod.DELETE, "/api/materiales/{id}").hasAnyRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/materiales/asignatura/{id}").hasAnyRole("ADMIN", "ESTUDIANTE")
	            
	            .requestMatchers(HttpMethod.GET, "/api/reportes/notas-promedio").hasAnyRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/reportes/historial-estudiante/{id}").hasAnyRole("ADMIN")
	            .requestMatchers(HttpMethod.GET, "/api/reportes/reporte-final/{cursoId}").hasAnyRole("ADMIN")
	            
	            
	            // Permisos de acceso para profesores
	            //.requestMatchers(HttpMethod.POST, "/api/materiales").hasRole("PROFESOR")
	            //.requestMatchers(HttpMethod.POST, "/api/notas").hasRole("PROFESOR")
	            //administra sus asignaturas
	           // .requestMatchers(HttpMethod.GET, "/api/asignaturas/{id}").hasRole("PROFESOR")
	            //.requestMatchers(HttpMethod.PUT, "/api/asignaturas/{id}").hasRole("PROFESOR")
	           // .requestMatchers(HttpMethod.DELETE, "/api/asignaturas/{id}").hasRole("PROFESOR")
	            
	            // Permisos de acceso para estudiantes
	            //.requestMatchers(HttpMethod.GET, "/api/asignaturas").hasRole("ESTUDIANTE")
	            //.requestMatchers(HttpMethod.GET, "/api/asignaturas/{id}").hasRole("ESTUDIANTE")
	            
	            //.requestMatchers(HttpMethod.GET, "/api/materiales/asignatura/{id}").hasRole("ESTUDIANTE")
	            
	            //.requestMatchers(HttpMethod.GET, "/api/notas/estudiante/{id}").hasRole("ESTUDIANTE")
	            //.requestMatchers(HttpMethod.GET, "/api/notas/{id}").hasRole("ESTUDIANTE")
	            
				.anyRequest().authenticated() )
	    		 .addFilter(new JwtAuthenticationFilter(authenticationManager())) // Ensure constructor matches
	    	     .addFilterAfter(new JwtValidationFilter(authenticationManager()), JwtAuthenticationFilter.class) // Ensure constructor matches
			.csrf(config -> config.disable() )
			.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
			.build();
	        
	}
	
	

	
}
