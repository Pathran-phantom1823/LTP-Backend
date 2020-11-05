package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.SkillsModel;
@Repository
public interface SkillsRepository extends JpaRepository <SkillsModel, Long>{
    
}
