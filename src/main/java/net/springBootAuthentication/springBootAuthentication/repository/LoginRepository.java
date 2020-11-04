package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
@Repository
public interface LoginRepository extends JpaRepository<RegisterModel, Long>{
	public RegisterModel findByusername(String username);
}
