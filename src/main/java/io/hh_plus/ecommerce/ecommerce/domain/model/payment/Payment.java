package io.hh_plus.ecommerce.ecommerce.domain.model.payment;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="discounted_price", nullable = false)
    private int discountedPrice = 0;

    @Column(nullable = false)
    private int price;

    @Column(name="paid_at", nullable = false)
    private LocalDateTime paidAt;

    @Column(name="refund_at")
    private LocalDateTime refundAt;
}
