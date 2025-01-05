package io.hh_plus.ecommerce.ecommerce.controller.payment;

import io.hh_plus.ecommerce.ecommerce.domain.service.payment.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("payments")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // 결제 API
    @PostMapping()
    public ResponseEntity<String> createPayment() {
        return ResponseEntity.ok("결제 생성 API");
    }

}
