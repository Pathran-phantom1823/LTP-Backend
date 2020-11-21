package net.springBootAuthentication.springBootAuthentication.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.ProfileModel;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileModel, Long> {

    @Transactional
	@Modifying
    @Query(value = "{call getImage(:id)}", nativeQuery = true)
    String getImage(@Param("id") Long id);

    @Transactional
	@Modifying
    @Query(value = "{call getAdminImage(:id)}", nativeQuery = true)
    String getAdminImage(@Param("id") Long id);
}