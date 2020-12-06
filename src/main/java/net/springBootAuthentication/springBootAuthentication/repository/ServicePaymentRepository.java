package net.springBootAuthentication.springBootAuthentication.repository;

import net.springBootAuthentication.springBootAuthentication.model.ServicePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicePaymentRepository extends JpaRepository<ServicePayment, Long> {
}
