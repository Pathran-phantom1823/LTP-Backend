package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobs;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomUser;
import net.springBootAuthentication.springBootAuthentication.customModel.Register;
import net.springBootAuthentication.springBootAuthentication.model.Jobs;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long>{
    
    @Query(value = "{call getFile(:id)}", nativeQuery = true)
    public List<Jobs> getFile(@Param("id") Long id);


    @Query(value = "{call getFileThroughParameter(:file)}", nativeQuery = true)
    public List<String> getFileThroughParameter(@Param("file") String file);
    
    @Query(value = "call getAllJobs(:id)", nativeQuery = true)
    public List<CustomJobs> getAllJobs(@Param("id") Long id);

    @Query(value = "{call getJobById(:id)}", nativeQuery = true)
    List<CustomJobs> getJobById(@Param("id") Long id);

    @Query(value = "{call getBids(:id)}", nativeQuery = true)
    List<CustomUser> getBids(@Param("id") Long id);

    @Query(value = "{call getMyJobs(:id)}", nativeQuery = true)
    List<CustomJobs> getMyJobs(@Param("id") Long id);

    
}
