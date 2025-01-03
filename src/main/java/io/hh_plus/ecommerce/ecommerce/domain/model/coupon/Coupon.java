package io.hh_plus.ecommerce.ecommerce.domain.model.coupon;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name= "coupon_type", nullable = false)
    private CouponType couponType;

    @Column(name = "current_stock", nullable = false)
    private int currentStock = 0;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

}
