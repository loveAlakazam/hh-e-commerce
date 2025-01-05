package io.hh_plus.ecommerce.ecommerce.domain.service.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private int price;
    private String description;
    private int currentStock;
    private String merchantName;
    private String merchantEmail;
    private String merchantPhoneNumber;

}
