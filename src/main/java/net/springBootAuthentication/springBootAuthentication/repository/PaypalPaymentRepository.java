package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.springBootAuthentication.springBootAuthentication.model.PaypalPayment;

public interface PaypalPaymentRepository extends JpaRepository<PaypalPayment, Long>{

	@Transactional
	@Modifying
	@Query(value = "{call spPaypalPayment(:amount, :currency, :plan, :details, :id)}" , nativeQuery = true)
    void pay(
    		@Param("amount") float amount, 
    		@Param("currency") String currency,
    		@Param("plan") String plan,
    		@Param("details") String details,
    		@Param("id") Long id
    		);
}
