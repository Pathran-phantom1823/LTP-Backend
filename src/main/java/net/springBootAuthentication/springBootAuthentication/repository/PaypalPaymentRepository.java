package net.springBootAuthentication.springBootAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.springBootAuthentication.springBootAuthentication.model.PaypalPayment;

public interface PaypalPaymentRepository extends JpaRepository<PaypalPayment, Long>{

}
