package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.SaveJob;

public interface SaveJobRepository extends JpaRepository<SaveJob, Long>{
    
}
