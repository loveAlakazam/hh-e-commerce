package io.hh_plus.ecommerce.ecommerce.domain.model.user;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.InvalidRequestException;
import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.point.Point;
import io.hh_plus.ecommerce.ecommerce.domain.service.user.exception.UserErrorCode;
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
        if(userId <= 0) throw new InvalidRequestException(UserErrorCode.USER_ID_IS_POSITIVE_NUMBER_POLICY);
    }
    /**
     * validateName: 유저명 검증함수
     * @param name String
     */
    public static void validateName(String name) {
       if(name == null || name.trim().isEmpty()) throw new InvalidRequestException(UserErrorCode.USER_NAME_IS_REQUIRED_POLICY);
       if(name.length() > MAX_NAME_LENGTH) throw new InvalidRequestException(UserErrorCode.USER_NAME_IS_LONGER_THAN_MINIMUM_LENGTH_SIZE_POLICY);
   }


}
