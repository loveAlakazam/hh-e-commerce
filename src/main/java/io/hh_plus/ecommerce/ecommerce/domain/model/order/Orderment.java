package io.hh_plus.ecommerce.ecommerce.domain.model.order;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.payment.Payment;
import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orderment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 주문 전체수량 = 전체 주문아이템 개수
    @Column(nullable = false)
    private int quantity;

    // 전체 주문금액 = 전체 주문아이템 주문가격
    @Column(nullable = false)
    private int price;

    // 주문 상태
    @Enumerated(EnumType.STRING)
    @Column(name="payment_status")
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;


    // user:order = 1:N
    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    // order:payment = 1:1
    @OneToOne
    @JoinColumn(name="payment_id")
    private Payment payment;

}
