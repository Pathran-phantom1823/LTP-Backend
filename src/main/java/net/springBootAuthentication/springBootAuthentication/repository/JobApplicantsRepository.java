package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import javax.transaction.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomWorkedJobs;
import net.springBootAuthentication.springBootAuthentication.customModel.GetPaymentDetailsModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobApplicant;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobs;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomQuotationAssignment;
import net.springBootAuthentication.springBootAuthentication.model.JobApplicants;
@Repository
public interface JobApplicantsRepository extends JpaRepository<JobApplicants, Long> {
    
    
    @Query(value = "{call getAcceptedJobs(:id)}", nativeQuery = true)
    List<CustomJobs> getAcceptedJobs(@Param("id") Long id);

    @Query(value = "{call getNotAssignedJobs(:id)}", nativeQuery = true)
    List<CustomJobs> getNotAssignedJobs(@Param("id") Long id);

    @Transactional
    @Procedure(procedureName = "updateAplicantAssigned")
    void updateAplicantAssigned(@Param("id") Long id);

    @Query(value = "{call getMyJobHistory(:id)}", nativeQuery = true)
    List<CustomJobApplicant> getMyJobHistory(@Param("id") Long id);

    
    @Query(value = "{call getMembersAccount(:roleType)}", nativeQuery = true)
    List<CustomQuotationAssignment> getMembersAccount(@Param("roleType") String roleType);

    @Query(value = "{call getWorkedJobs()}", nativeQuery = true)
    List<CustomWorkedJobs> getWorkedJobs();
    
    @Query(value = "{call spGetPaymentDetails(:id, :jobId)}", nativeQuery = true)
    List<GetPaymentDetailsModel> getPaymentDetails(@Param("id") Long id, @Param("jobId") Long jobId);

}
