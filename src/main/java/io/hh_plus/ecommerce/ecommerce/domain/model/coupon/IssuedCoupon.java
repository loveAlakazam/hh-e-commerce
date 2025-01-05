package io.hh_plus.ecommerce.ecommerce.domain.model.coupon;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.payment.Payment;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 유저가 보유한 쿠폰을 의미함
 *
 * 유저는 서로다른 종류의 쿠폰을 각각 1개씩만 소유할 수 있다.
 * 유저는 동일한 종류의 쿠폰을 2개이상 소유할 수 없다.
 * */
@Table(
        name = "issued_coupon",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "coupon_id"})
        }
)
@Getter
@Setter
@AllArgsConstructor
public class IssuedCoupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 쿠폰 사용날짜
    @Column(name = "used_at", nullable = true)
    private LocalDateTime usedAt = null;

    // 쿠폰 만료날짜
    @Column(name = "expired_at", nullable = true)
    private LocalDateTime expiredAt = null;

    // user:issued_coupon=1:N
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // coupon:issued_coupon:1:N
    @ManyToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment payment = null;
}
