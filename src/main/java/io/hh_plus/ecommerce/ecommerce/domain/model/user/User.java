package io.hh_plus.ecommerce.ecommerce.domain.model.user;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.coupon.IssuedCoupon;
import io.hh_plus.ecommerce.ecommerce.domain.model.point.Point;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private static final int MAX_NAME_LENGTH = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true , nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(unique = true , nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Point point;


    // User 가 저장된 이후 Point 데이터를 자동으로 생성
    @PostPersist
    public void createPointAfterUser() {
        if (this.point == null) {
            Point point = new Point();
            point.setUser(this);
            point.setValue(0); // 포인트 생성시 0 원으로 생성
            this.point = point;
        }
    }


    // 검증함수

    /**
     * validateUserId: 유저 식별자 검증함수
     * @param userId long
     */
    public static void validateUserId(long userId) {
        if(userId <= 0) throw new IllegalArgumentException("유저 식별자는 양수여야합니다.");
    }
    /**
     * validateName: 유저명 검증함수
     * @param name String
     */
    public static void validateName(String name) {
       if(name == null || name.trim().isEmpty()) throw new IllegalArgumentException("이름은 필수입니다.");
       if(name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("이름은 최대 " + MAX_NAME_LENGTH + "자 까지 가능합니다.");
   }


}
