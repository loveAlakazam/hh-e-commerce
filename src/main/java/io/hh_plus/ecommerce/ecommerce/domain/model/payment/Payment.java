package io.hh_plus.ecommerce.ecommerce.domain.model.payment;

import io.hh_plus.ecommerce.ecommerce.domain.model.coupon.IssuedCoupon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 쿠폰 할인적용 금액
    @Column(name="discounted_price", nullable = false)
    private int discountedPrice = 0;

    // 실제결제 금액 = 주문금액(order.price) - 쿠폰 할인적용 금액(discounted_price)
    @Column(nullable = false)
    private int price;

    // 결제날짜
    @Column(name="paid_at", nullable = false)
    private LocalDateTime paidAt = null;

    // 환불날짜
    @Column(name="refund_at", nullable = false)
    private LocalDateTime refundAt = null;

    // issued_coupon:payment=1:1
    // 쿠폰은 1개만 사용가능하며 중복으로 사용할 수 없다.
    @OneToOne(mappedBy = "payment")
    private IssuedCoupon issuedCoupon;
}
