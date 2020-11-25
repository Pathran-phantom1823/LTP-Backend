package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomTranslators;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomUser;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomUserInterface;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.model.RegisterModel;

@Repository
public interface RegisterRepository  extends JpaRepository<RegisterModel, Long>{
    public RegisterModel findByUsername(String username);
    public Optional<RegisterModel> findById(long id);

    @Transactional
    @Query (value =  "{call spRetrieveRoleIdByRoleType(:roletype)}", nativeQuery = true)
    Integer getRoleIdByType(@Param("roletype") String roletype);

    @Transactional
    @Query(value = "{call getMembers(:id)}", nativeQuery = true)
    List<RegisterModel> getMembers(@Param("id") Long id);

    @Transactional
    @Query(value = "{call getJobById(:id)}", nativeQuery = true)
    List<RegisterModel> getOwnerofJobs(@Param("id") Long id);

    @Transactional
    @Query(value = "{call getEmail(:email)}", nativeQuery = true)
    Long getEmail(@Param("email") String email);

    @Transactional
    @Query(value = "{call getAllTranslators(:id)}", nativeQuery = true)
    List<CustomTranslators> getAllTranslators(@Param("id") Long id);

    @Transactional
    @Query(value = "{call getAgencyTranslators(:id)}", nativeQuery = true)
    List<CustomTranslators> getAgencyTranslators(@Param("id") Long id);

    @Transactional
    @Query(value = "{call getCurrentUser(:id)}", nativeQuery = true)
    CustomUser getCurrentUser(@Param("id") Long id);

    @Transactional
    @Query(value = "{call checkUsernameExist(:username)}", nativeQuery = true)
    String checkUsernameExist(@Param("username") String username);

    @Transactional
    @Query(value = "{call checkEmailExist(:email)}", nativeQuery = true)
    String checkEmailExist(@Param("email") String email);

    @Transactional
    @Query(value = "{call getAgents()}", nativeQuery = true)
    List<RegisterModel> getAgents();

    @Transactional
    @Query(value = "{call getAllUser()}", nativeQuery = true)
    List<CustomUserInterface> getAllUser();
}
