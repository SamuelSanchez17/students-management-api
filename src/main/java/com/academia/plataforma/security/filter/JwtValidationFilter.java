package com.academia.plataforma.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.academia.plataforma.security.TokenJwtConfig.*;

import java.io.IOException;
import java.util.*;

public class JwtValidationFilter extends BasicAuthenticationFilter{

	public JwtValidationFilter(AuthenticationManager authenticationManager) 
	{
		super(authenticationManager);
		
	}

	
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException 
	{

        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(PREFIX_TOKEN)) 
        {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "");

        try 
        {
            Claims claims = Jwts.parser()
                                .verifyWith(SECRET_KEY)
                                .build()
                                .parseSignedClaims(token)
                                .getPayload();

            String username = claims.get("username", String.class);   // ← lo pusimos en el token
            String authoritiesJson = claims.get("authorities", String.class); // array-JSON

         // Deserialize the JSON string into a List of role names
            List<String> roleNames = new ObjectMapper().readValue(authoritiesJson, List.class);

            // Convert the List of role names into a List of SimpleGrantedAuthority
            Collection<SimpleGrantedAuthority> authorities = roleNames.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            authToken.setDetails(new org.springframework.security.web.authentication.WebAuthenticationDetailsSource()
                                 .buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);

            chain.doFilter(request, response);

        } catch (JwtException | IOException e) 
        {
            Map<String, String> body = Map.of(
                    "error", e.getMessage(),
                    "message", "El token JWT no es válido o está expirado"
            );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        }
        
    }
	
	
	
}
