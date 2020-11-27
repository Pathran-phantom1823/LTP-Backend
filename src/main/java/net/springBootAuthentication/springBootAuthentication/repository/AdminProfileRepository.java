package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomAdminInterface;
import net.springBootAuthentication.springBootAuthentication.customModel.PaymentSummaryModel;
import net.springBootAuthentication.springBootAuthentication.model.AdminProfileModel;
import net.springBootAuthentication.springBootAuthentication.model.UsersSummaryModel;


@Repository
public interface AdminProfileRepository extends JpaRepository<AdminProfileModel, Long> {

    
    @Query(value = "{call getImage(:id)}", nativeQuery = true)
    String getImage(@Param("id") Long id);

    
    @Query(value = "{call checkAdminId(:id)}", nativeQuery = true)
    Long checkAdminId(@Param("id") Long id);

    
    @Query(value = "{call retrieveProfileAdmin(:id)}", nativeQuery = true)
    List<CustomAdminInterface> retrieveProfileAdmin(@Param("id") Long id);
    
    
    @Transactional
	@Query(value = "{call spUsersSummary()}" , nativeQuery = true)
    ArrayList<UsersSummaryModel> summarize();
    
    @Transactional
	@Query(value = "{call spPaymentSummary()}" , nativeQuery = true)
    ArrayList<PaymentSummaryModel> paymentSummarize();
}