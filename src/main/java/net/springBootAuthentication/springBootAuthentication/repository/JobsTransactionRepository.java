package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.springBootAuthentication.springBootAuthentication.model.JobTransactionModel;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobHistory;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomTransactionJobs;

import javax.transaction.Transactional;

public interface JobsTransactionRepository extends JpaRepository<JobTransactionModel, Long> {
    
    
    @Query(value = "{call getAssignedJobs(:id)}", nativeQuery = true)
    List<CustomTransactionJobs> getAssignedJobs(@Param("id") Long id);

    
    @Query(value = "{call getAssignedJobsById(:id)}", nativeQuery = true)
    List<CustomTransactionJobs> getAssignedJobsById(@Param("id") Long id);

    
    @Query(value = "{call getJobHistory(:id)}", nativeQuery = true)
    List<CustomJobHistory> getJobHistory(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "{call uploadFinishedOrgFile(:applicantId, :jobId, :finishedFile, :date)}", nativeQuery = true)
    void uploadFinishedOrgFile(@Param("applicantId") Long applicantId, @Param("jobId") Long jobId, @Param("finishedFile") String finishedFile, @Param("date") String date);

    @Query(value = "call getAgencyFinishedJobs(:id)", nativeQuery = true)
    List<CustomJobHistory> getAgencyFinishedJobs(@Param("id") Long id);
}
