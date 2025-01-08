package io.hh_plus.ecommerce.ecommerce.domain.model.product;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.InvalidRequestException;
import io.hh_plus.ecommerce.ecommerce.domain.model.common.BaseEntity;
import io.hh_plus.ecommerce.ecommerce.domain.model.order.OrderItem;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.exception.ProductErrorCode;
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
public class Product extends BaseEntity {
    // 페이지 목록당 고정 limit 정의
    public static final int PAGINATION_LIMIT = 10;

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

    // order_item:product=1:1
    @OneToOne(mappedBy= "product" )
    private OrderItem orderitem;

    // 생성자
    public Product(long id, String name, int price, String description, int currentStock, String merchantName, String merchantEmail, String merchantPhoneNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.currentStock = currentStock;
        this.merchantName = merchantName;
        this.merchantEmail = merchantEmail;
        this.merchantPhoneNumber = merchantPhoneNumber;
    }

    // 검증함수
    /**
     * validateProductId : 상품 식별자 검증함수
     * - 검증내용: 상품 식별자(Product.id)는 양수이다.
     *
     * @param productId long
     */
    public static void validateProductId(long productId) {
        if(productId <= 0) throw new InvalidRequestException(ProductErrorCode.PRODUCT_ID_POSITIVE_NUMBER_POLICY);
    }
    /**
     * validatePage : 상품목록 페이지 검증함수
     * - 검증내용: 상품목록 페이지(page)는 양수이다.
     *
     * @param page int
     */
    public static void validatePage(int page) {
        if(page <= 0) throw new InvalidRequestException(ProductErrorCode.PRODUCT_PAGE_POSITIVE_NUMBER_POLICY);
    }
}
