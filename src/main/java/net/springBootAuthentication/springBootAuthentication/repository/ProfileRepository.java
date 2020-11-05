package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.ProfileModel;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileModel, Long> {


}