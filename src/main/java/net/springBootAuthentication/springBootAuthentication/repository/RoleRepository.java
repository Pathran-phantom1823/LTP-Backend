package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Long>{
    
}
