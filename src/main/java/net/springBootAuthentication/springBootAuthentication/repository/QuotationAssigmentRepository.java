package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootAuthentication.springBootAuthentication.model.QuotationAssigmentModel;

@Repository
public interface QuotationAssigmentRepository extends JpaRepository<QuotationAssigmentModel, Long> {
    
}
