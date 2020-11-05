package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.RoleModel;
@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long>{
    
}
