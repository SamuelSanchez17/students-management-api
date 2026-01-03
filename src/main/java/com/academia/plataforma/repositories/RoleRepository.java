package com.academia.plataforma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.plataforma.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{

	Role findByRoleName(String roleName);
	
}
