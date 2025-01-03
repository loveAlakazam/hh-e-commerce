package io.hh_plus.ecommerce.ecommerce.controller.payment;

import io.hh_plus.ecommerce.ecommerce.domain.service.payment.PaymentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
