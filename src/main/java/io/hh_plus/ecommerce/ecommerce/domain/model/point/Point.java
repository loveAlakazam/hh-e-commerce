package io.hh_plus.ecommerce.ecommerce.domain.model.point;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Point extends BaseEntity {
    // 최소 충전금액
    public static final int MINIMUM_CHARGE_AMOUNT = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int value = 0; // 기본값 설정

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // 검증함수
    public static void validateChargeAmount(int amount) {
        if(amount < Point.MINIMUM_CHARGE_AMOUNT) throw new IllegalArgumentException("충전금액은 최소 1000원 이상이어야합니다.");
    }
}

