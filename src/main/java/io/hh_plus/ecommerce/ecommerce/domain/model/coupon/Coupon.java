package io.hh_plus.ecommerce.ecommerce.domain.model.coupon;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 쿠폰 타입
    @Enumerated(EnumType.STRING)
    @Column(name= "coupon_type", nullable = false)
    private CouponType couponType;

    // 쿠폰 재고량
    @Column(name = "current_stock", nullable = false)
    private int currentStock = 0;

    // 쿠폰 유효기간(일)
    @Column(name = "duration", nullable = false)
    private int duration = 7;

    // 쿠폰 할인 금액 / 퍼센트
    /**
     * 1. couponType = PERCENT 일때
     *      discount_value 는 할인율(%) 단위로 나타낸다.
     *      discount_value > 0 && discount_value < 90
     *
     * 2. couponType = AMOUNT 일때
     *      discount_value 는 할인금액(원) 단위로 나타낸다.
     *      discount_value > 1,000 && discount_value < 50,000
     */
    @Column(name = "discount_value", nullable = false)
    private int discountValue;

}
