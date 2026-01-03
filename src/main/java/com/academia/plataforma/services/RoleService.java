package com.academia.plataforma.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.academia.plataforma.entities.Role;

@Service
public interface RoleService extends JpaRepository<Role, Long>{

}
