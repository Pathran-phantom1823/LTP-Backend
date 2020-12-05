package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.ArrayList;
import java.util.List;

import net.springBootAuthentication.springBootAuthentication.customModel.JobCountInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomAdminInterface;
import net.springBootAuthentication.springBootAuthentication.customModel.PaymentSummaryModel;
import net.springBootAuthentication.springBootAuthentication.customModel.PaymentYearsSummary;
import net.springBootAuthentication.springBootAuthentication.customModel.QuotationChartModel;
import net.springBootAuthentication.springBootAuthentication.customModel.QuotationsYearsSummary;
import net.springBootAuthentication.springBootAuthentication.customModel.ReportsChartModel;
import net.springBootAuthentication.springBootAuthentication.customModel.ReportsYearsSummary;
import net.springBootAuthentication.springBootAuthentication.customModel.SalesChartModel;
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
    
    @Transactional
    @Query(value = "{call spSalesYears()}", nativeQuery = true)
    ArrayList<PaymentYearsSummary> paymentYearsSummary();
    
    @Transactional
    @Query(value = "{call spReportsYears()}", nativeQuery = true)
    ArrayList<ReportsYearsSummary> reportsYearsSummary();
    
    @Transactional
    @Query(value = "{call spQuotationsYears()}", nativeQuery = true)
    ArrayList<QuotationsYearsSummary> quotationsYearsSummary();
    
    @Transactional
    @Query(value = "{call spSalesChart(:month, :year)}", nativeQuery = true)
    ArrayList<SalesChartModel> salesChartSummary(@Param("month") int month, @Param("year") int year);
    
    @Transactional
    @Query(value = "{call spReportsChart(:month, :year)}", nativeQuery = true)
    ArrayList<ReportsChartModel> reportsChartSummary(@Param("month") int month, @Param("year") int year);
    
    @Transactional
    @Query(value = "{call spQuotationsChart(:month, :year)}", nativeQuery = true)
    ArrayList<QuotationChartModel> quotationChartSummary(@Param("month") int month, @Param("year") int year);

    @Transactional
    @Query(value = "{call spCountJobs()}" , nativeQuery = true)
    ArrayList<JobCountInterface> countJobs();
}