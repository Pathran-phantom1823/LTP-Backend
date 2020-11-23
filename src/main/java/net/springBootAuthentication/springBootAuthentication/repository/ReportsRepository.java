package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomReports;
import net.springBootAuthentication.springBootAuthentication.model.ReportsModel;

@Repository
public interface ReportsRepository  extends JpaRepository<ReportsModel, Long>{
    @Transactional
	@Modifying
    @Query(value = "{call getReports()}", nativeQuery = true)
    public List<CustomReports> getReports();
}

