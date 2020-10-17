package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
