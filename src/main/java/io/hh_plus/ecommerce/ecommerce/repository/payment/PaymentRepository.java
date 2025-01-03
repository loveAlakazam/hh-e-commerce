package io.hh_plus.ecommerce.ecommerce.repository.payment;

import io.hh_plus.ecommerce.ecommerce.domain.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
