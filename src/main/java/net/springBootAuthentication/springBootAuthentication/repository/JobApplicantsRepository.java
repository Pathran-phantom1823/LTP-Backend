package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.JobApplicants;

public interface JobApplicantsRepository extends JpaRepository<JobApplicants, Long> {
    
}
