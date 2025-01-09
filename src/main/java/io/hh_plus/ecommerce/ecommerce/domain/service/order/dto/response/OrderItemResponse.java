package io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private long id;
    private int quantity;
    private int price;
    private String productName;
}
