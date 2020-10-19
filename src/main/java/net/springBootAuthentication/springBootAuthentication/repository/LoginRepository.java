package net.springBootAuthentication.springBootAuthentication.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;

public interface LoginRepository extends JpaRepository<RegisterModel, Long>{
	public RegisterModel findByusername(String username);
}
