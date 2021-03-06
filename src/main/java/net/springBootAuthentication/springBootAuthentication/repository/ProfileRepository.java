package net.springBootAuthentication.springBootAuthentication.repository;

import java.io.File;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.ProfileModel;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomProfileInterface;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileModel, Long> {

    
    @Query(value = "{call getImage(:id)}", nativeQuery = true)
    File getImage(@Param("id") Long id);

    
    @Query(value = "{call getAdminImage(:id)}", nativeQuery = true)
    File getAdminImage(@Param("id") Long id);

    @Query(value = "{call spCheckAccountExisted(:id)}", nativeQuery = true)
    Long checkAccountExisted(@Param("id") Long id);

    @Query(value = "{call retrieveProfile(:id)}", nativeQuery = true)
    List<CustomProfileInterface> getProfileById(@Param("id")Long id);

    // @Query(value = "{call getProfileImage(:id)}", nativeQuery = true)
    // String getProfileImage(@Param("id") Long id);

}