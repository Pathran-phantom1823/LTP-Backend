package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.model.JobTransactionModel;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobHistory;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomTransactionJobs;

public interface JobsTransactionRepository extends JpaRepository<JobTransactionModel, Long> {
    
    @Transactional
    @Query(value = "{call getAssignedJobs(:id)}", nativeQuery = true)
    List<CustomTransactionJobs> getAssignedJobs(@Param("id") Long id);

    @Transactional
    @Query(value = "{call getAssignedJobsById(:id)}", nativeQuery = true)
    List<CustomTransactionJobs> getAssignedJobsById(@Param("id") Long id);

    @Transactional
    @Query(value = "{call getJobHistory(:id)}", nativeQuery = true)
    List<CustomJobHistory> getJobHistory(@Param("id") Long id);
}
