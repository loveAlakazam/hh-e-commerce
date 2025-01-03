package io.hh_plus.ecommerce.ecommerce.domain.model.order;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.payment.Payment;
import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int quantity;

    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name="payment_status")
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;


    @ManyToOne
    @Column(name= "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;


    @OneToOne
    @Column(name="payment_id", nullable = false)
    private Payment payment;

}
