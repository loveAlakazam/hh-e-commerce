package io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request;

import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderItemRequestDto {
    private long productId;
    private int quantity;

    public CreateOrderItemRequestDto(long productId, int quantity) {
        Product.validateProductId(productId);

        this.productId = productId;
        this.quantity = quantity;
    }
}
