package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;
@Repository
public interface LoginRepository extends JpaRepository<RegisterModel, Long>{
	
	@Query(value = "{call spLogin(:username)}", nativeQuery = true)
	RegisterModel findByUsername(@Param("username") String username);
}
