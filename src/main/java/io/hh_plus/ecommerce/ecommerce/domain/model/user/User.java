package io.hh_plus.ecommerce.ecommerce.domain.model.user;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class User extends BaseEntity {
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

    @Column(nullable = false)
    private int currentPoint = 0; // 기본값 설정

}
