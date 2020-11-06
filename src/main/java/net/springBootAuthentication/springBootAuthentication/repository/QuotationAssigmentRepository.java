package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomAssignedQuotation;
import net.springBootAuthentication.springBootAuthentication.model.QuotationAssigmentModel;

@Repository
public interface QuotationAssigmentRepository extends JpaRepository<QuotationAssigmentModel, Long> {

    @Query(value = "{call getMyAssignedQuote(:id)}", nativeQuery = true)
    List<CustomAssignedQuotation> getMyAssignedQuotations(@Param("id") Long id);   

    @Query(value = "{call  getQuotationPrimaryId(:id)}", nativeQuery = true)
    Long  getQuotationPrimaryId(@Param("id") Long id);
}
