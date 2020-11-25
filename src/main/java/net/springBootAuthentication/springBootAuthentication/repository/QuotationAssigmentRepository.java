package net.springBootAuthentication.springBootAuthentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.customModel.CustomAssignedQuotation;
import net.springBootAuthentication.springBootAuthentication.model.QuotationAssigmentModel;

@Repository
public interface QuotationAssigmentRepository extends JpaRepository<QuotationAssigmentModel, Long> {

    @Transactional
    @Query(value = "{call getMyAssignedQuote(:id)}", nativeQuery = true)
    List<CustomAssignedQuotation> getMyAssignedQuotations(@Param("id") Long id);   

    @Transactional
    @Query(value = "{call  getQuotationPrimaryId(:id)}", nativeQuery = true)
    Long  getQuotationPrimaryId(@Param("id") Long id);
}
