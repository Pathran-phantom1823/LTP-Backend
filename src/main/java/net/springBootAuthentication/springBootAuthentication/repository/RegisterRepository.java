package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;

@Repository
public interface RegisterRepository  extends JpaRepository<RegisterModel, Long>{
    
}
