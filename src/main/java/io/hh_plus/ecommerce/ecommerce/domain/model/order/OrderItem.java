package io.hh_plus.ecommerce.ecommerce.domain.model.order;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 주문수량
    @Column(nullable = false)
    private int quantity;

    // 주문금액
    @Column(nullable = false)
    private int price;

    // order:order_item=1:N
    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Orderment order;

    // order_item:product=1:1
    @OneToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;
}
