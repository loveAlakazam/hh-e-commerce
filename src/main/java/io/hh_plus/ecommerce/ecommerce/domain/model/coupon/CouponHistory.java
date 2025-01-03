package io.hh_plus.ecommerce.ecommerce.domain.model.coupon;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * 유저는 서로다른 종류의 쿠폰을 각각 1개씩만 소유할 수 있다.
 * 유저는 동일한 종류의 쿠폰을 2개이상 소유할 수 없다.
 * */
@Table(
        name = "coupon_history",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "coupon_id"})
        }
)
public class CouponHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @Column(name= "used_at", nullable = true)
    private LocalDateTime usedAt;

}
