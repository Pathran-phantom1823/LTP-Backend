package net.springBootAuthentication.springBootAuthentication.repository;

import org.apache.commons.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.EducationModel;

@Repository
public interface EducationRepository extends JpaRepository<EducationModel, Long>{
    
}
