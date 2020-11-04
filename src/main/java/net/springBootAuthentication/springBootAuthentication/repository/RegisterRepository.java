package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;

@Repository
public interface RegisterRepository  extends JpaRepository<RegisterModel, Long>{
    public RegisterModel findByUsername(String username);

    @Query (value =  "{call spRetrieveRoleIdByRoleType(:roletype)}", nativeQuery = true)
    Integer getRoleIdByType(@Param("roletype") String roletype);

    @Query(value = "{call getMembers(:id)}", nativeQuery = true)
    List<RegisterModel> getMembers(@Param("id") Long id);

    @Query(value = "{call getJobById(:id)}", nativeQuery = true)
    List<RegisterModel> getOwnerofJobs(@Param("id") Long id);

}
