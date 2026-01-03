package com.academia.plataforma.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.plataforma.Dtos.RoleDTO;
import com.academia.plataforma.Dtos.UserDTO;
import com.academia.plataforma.entities.User;
import com.academia.plataforma.repositories.RoleRepository;
import com.academia.plataforma.entities.Role;
import com.academia.plataforma.services.UserService;
import com.academia.plataforma.utils.ValidationError;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository; // Assuming you have a RoleRepository to fetch roles
	
	@RequestMapping
	public List<UserDTO> list()
	{
		return userService.findAll().stream()
	            .map(user -> new UserDTO(
	                    user.getUsername(),
	                    user.getEmail(),
	                    user.getPassword(), // Password is not included in responses
	                    user.getRole() != null ? new RoleDTO(user.getRole().getId(), user.getRole().getRoleName()) : null
	            ))
	            .collect(Collectors.toList());
	}
	
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable("id") Long id)
	{
		Optional<User> userOptional = userService.findById(id);
		
		if(userOptional.isPresent())
		{
			User user = userOptional.get();
			
			UserDTO userDTO = new UserDTO(
					user.getUsername(),
					user.getEmail(),
					user.getPassword(),
					user.getRole() != null ? new RoleDTO(user.getRole().getId(), user.getRole().getRoleName()) : null
					);
			return ResponseEntity.ok(userDTO); // retorna un 200 OK con el usuario encontrado
		}
		
		return ResponseEntity.notFound().build();
	}
	
	


@PutMapping("/{id}")
public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @RequestBody UserDTO userDTO, BindingResult result) {

    if (result.hasFieldErrors()) {
        return ValidationError.validation(result);
    }

    // crear el objeto User y asignar los valores del DTO
    User userToUpdate = new User();
    userToUpdate.setUsername(userDTO.getUsername());
    userToUpdate.setEmail(userDTO.getEmail());
    userToUpdate.setPassword(userDTO.getPassword());

    // verificar si el rol existe y asignarlo al usuario
    if (userDTO.getRole() != null && userDTO.getRole().getId() != null) {
        Optional<Role> roleOptional = roleRepository.findById(userDTO.getRole().getId());
        if (roleOptional.isPresent()) {
            userToUpdate.setRole(roleOptional.get());
        } else {
            return ResponseEntity.badRequest().body("Role with ID " + userDTO.getRole().getId() + " does not exist.");
        }
    }

    // llama al servicio para actualizar el usuario con su id
    Optional<User> updatedUserOptional = userService.update(id, userToUpdate);

    // Verfica si el usuario fue actualizado exitosamente
    if (updatedUserOptional.isPresent()) {
        User updatedUser = updatedUserOptional.get();

        // Convierte al usuario actualizado a un DTO para la response en postman
        UserDTO responseDTO = new UserDTO(
                updatedUser.getUsername(),
                updatedUser.getEmail(),
                updatedUser.getPassword(), // Exclude the password from the response
                updatedUser.getRole() != null ? new RoleDTO(updatedUser.getRole().getId(), updatedUser.getRole().getRoleName()) : null
        );

        return ResponseEntity.ok(responseDTO); 
    }

    return ResponseEntity.notFound().build();
}


	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id)
	{
		Optional<User> userOptional = userService.delete(id);
		
		if (userOptional.isPresent()) 
			return ResponseEntity.ok(userOptional.get() );
		
		return ResponseEntity.noContent().build();
	}

}
