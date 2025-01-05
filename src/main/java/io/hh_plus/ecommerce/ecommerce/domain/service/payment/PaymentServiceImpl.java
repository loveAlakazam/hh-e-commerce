package io.hh_plus.ecommerce.ecommerce.domain.service.payment;

import io.hh_plus.ecommerce.ecommerce.repository.payment.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements  PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
