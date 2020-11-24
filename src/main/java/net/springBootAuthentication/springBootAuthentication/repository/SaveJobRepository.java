package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomJobs;
import net.springBootAuthentication.springBootAuthentication.model.SaveJob;
@Repository
public interface SaveJobRepository extends JpaRepository<SaveJob, Long>{
    
    @Transactional
    @Query(value = "{call getSaveJobs(:id)}", nativeQuery = true)
    List<CustomJobs> getSaveJobs(@Param("id") Long id);
}
