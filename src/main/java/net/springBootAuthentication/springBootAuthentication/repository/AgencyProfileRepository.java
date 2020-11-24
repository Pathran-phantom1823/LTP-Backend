package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomAgencyProfileInterface;
import net.springBootAuthentication.springBootAuthentication.model.AgencyProfileModel;

@Repository
public interface AgencyProfileRepository extends JpaRepository<AgencyProfileModel, Long>{
    @Transactional
    @Query(value = "{call getAgencyProfile(:id)}", nativeQuery = true)
    List<CustomAgencyProfileInterface> getAgencyProfile(@Param("id") Long id);

    @Transactional
    @Query(value = "{call checkAgencyExisted(:id)}", nativeQuery = true)
    Long checkAgencyExisted(@Param("id") Long id);

    @Transactional
    @Query(value = "{call getAgencyImage(:id)}", nativeQuery = true)
    String getAgencyImage(@Param("id") Long id);
}
