package net.springBootAuthentication.springBootAuthentication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import net.springBootAuthentication.springBootAuthentication.model.Registration;

@Repository
public interface UserRepository extends JpaRepository<Registration, Long>{
    public Registration findByUsername(String username);

    @Query(value = "{call getMembers(:id)}", nativeQuery = true)
    List<Registration> getMembers(@Param("id") Long id);


    @Query(value = "{call getJobById(:id)}", nativeQuery = true)
    List<Registration> getOwnerofJobs(@Param("id") Long id);
}
