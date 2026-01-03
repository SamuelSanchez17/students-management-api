package com.academia.plataforma.security.filter;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.academia.plataforma.entities.User;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import static com.academia.plataforma.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	
	
	//constructor que recibe el AuthenticationManager para autenticar al usuario
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) 
	{
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/api/auth/login"); // Set the login endpoint
	}

	
	
	//este metodo se encarga de autenticar al usuario y se agrega en Source > Override/implement methods > attemptAuthentication
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException 
	{
		User user = null;
	    String username = null;
	    String password = null;
	    
	    try {
	        user = new ObjectMapper().readValue(request.getInputStream(), User.class);
	        username = user.getUsername();
	        password = user.getPassword();
	        
	    } catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.io.IOException e) {
			throw new AuthenticationServiceException("Error al leer las credenciales del usuario", e);
		}
		

		if (username == null || password == null) {
			throw new BadCredentialsException("El nombre de usuario o la contraseña no pueden ser nulos");
		}

		
		 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		 
		return authenticationManager.authenticate(authToken);
	}



	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws java.io.IOException, ServletException 
	{
		org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		
		String username = userDetails.getUsername();
		
		
		// Convierte los roles a una lista de Strings
	    Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
	    List<String> roleNames = roles.stream()
	            .map(GrantedAuthority::getAuthority)
	            .toList();
	    
	    // Serialize roles as a JSON string
	    String authoritiesJson = new ObjectMapper().writeValueAsString(roleNames);
		 
		 Claims claims = Jwts.claims()
				.add("authorities", authoritiesJson ) //se convirtieron los roles de usuario a un String JSON para verlo en el token JWT
				.add("username", username)
				.build();
		 
		 String token = Jwts.builder()
				 			.subject(username)
				 			.claims(claims)
				 			.expiration(new Date(System.currentTimeMillis() + 3600000) ) //el token expira en una hora
				 			.issuedAt(new Date())
				 			.signWith(SECRET_KEY) //se debe definir el SECRET_KEY en la clase TokenJwtConfig
				 			.compact();
		
		 
		 response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
		 
		 Map<String, String> body = new HashMap<>();
		 body.put("token", token);
		 body.put("username", username);
		 body.put("message", String.format("Bienvenido %s, has iniciado sesión correctamente", username));
		 
		 response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		 response.setContentType(CONTENT_TYPE);
		 response.setStatus(200); //Status 200 OK
		 
	}



	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws java.io.IOException, ServletException 
	{
		Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticación: usuario o contraseña inválidos");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        
        response.setStatus(401); // Status 401 Unauthorized
        response.setContentType(CONTENT_TYPE);
        
	}

	
	
	
	
	
}
