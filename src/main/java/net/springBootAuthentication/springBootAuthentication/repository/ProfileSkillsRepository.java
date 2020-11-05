package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.ProfileSkillsModel;
@Repository
public interface ProfileSkillsRepository extends JpaRepository <ProfileSkillsModel, Long> {
    
}
