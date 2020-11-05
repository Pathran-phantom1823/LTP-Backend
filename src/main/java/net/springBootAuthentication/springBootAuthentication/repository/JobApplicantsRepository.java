package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobApplicant;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobs;

import net.springBootAuthentication.springBootAuthentication.model.JobApplicants;
@Repository
public interface JobApplicantsRepository extends JpaRepository<JobApplicants, Long> {
    
    @Query(value = "{call getAcceptedJobs(:id)}", nativeQuery = true)
    List<CustomJobs> getAcceptedJobs(@Param("id") Long id);

    @Query(value = "{call getMyJobHistory(:id)}", nativeQuery = true)
    List<CustomJobApplicant> getMyJobHistory(@Param("id") Long id);

}
