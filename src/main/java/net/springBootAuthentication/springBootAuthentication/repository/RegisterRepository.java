package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomTranslators;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomUser;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;

@Repository
public interface RegisterRepository  extends JpaRepository<RegisterModel, Long>{
    public RegisterModel findByUsername(String username);

    @Query(value = "{call getMembers(:id)}", nativeQuery = true)
    List<RegisterModel> getMembers(@Param("id") Long id);


    @Query(value = "{call getJobById(:id)}", nativeQuery = true)
    List<RegisterModel> getOwnerofJobs(@Param("id") Long id);

    
    @Query(value = "{call getEmail(:email)}", nativeQuery = true)
    Long getEmail(@Param("email") String email);
    
    @Query (value =  "{call spRetrieveRoleIdByRoleType(:roletype)}", nativeQuery = true)
    Integer getRoleIdByType(@Param("roletype") String roletype);

    @Query(value = "{call getAllTranslators(:id)}", nativeQuery = true)
    List<CustomTranslators> getAllTranslators(@Param("id") Long id);

    @Query(value = "{call getCurrentUser(:id)}", nativeQuery = true)
    CustomUser getCurrentUser(@Param("id") Long id);
}
