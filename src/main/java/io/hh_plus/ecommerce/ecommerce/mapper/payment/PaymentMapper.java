package io.hh_plus.ecommerce.ecommerce.mapper.payment;

import io.hh_plus.ecommerce.ecommerce.domain.model.payment.Payment;
import io.hh_plus.ecommerce.ecommerce.domain.service.payment.dto.response.PaymentResponse;

public class PaymentMapper {
    public static PaymentResponse toPaymentResponse(Payment payment) {
        if(payment == null) return null;
        return PaymentResponse.builder()
                .discountedPrice(payment.getDiscountedPrice())
                .price(payment.getPrice())
                .paidAt(payment.getPaidAt() != null ? payment.getPaidAt() : null)
                .build();
    }
}
