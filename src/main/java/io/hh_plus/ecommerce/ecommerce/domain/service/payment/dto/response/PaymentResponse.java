package io.hh_plus.ecommerce.ecommerce.domain.service.payment.dto.response;

import io.hh_plus.ecommerce.ecommerce.domain.model.coupon.IssuedCoupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private int discountedPrice;
    private int price;
    private LocalDateTime paidAt;
    private LocalDateTime refundAt;
    private IssuedCoupon issuedCoupon;
}
