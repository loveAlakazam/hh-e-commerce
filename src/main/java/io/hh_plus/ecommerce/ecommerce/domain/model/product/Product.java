package io.hh_plus.ecommerce.ecommerce.domain.model.product;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int currentStock  = 0;

    @Column(nullable = false)
    private String merchantName;

    @Column(nullable = false)
    private String merchantEmail;

    @Column(nullable = false)
    private String merchantPhoneNumber;


    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
