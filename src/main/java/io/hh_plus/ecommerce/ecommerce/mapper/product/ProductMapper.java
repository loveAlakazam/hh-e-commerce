package io.hh_plus.ecommerce.ecommerce.mapper.product;

import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.dto.response.ProductResponse;
import lombok.Builder;


public class ProductMapper {
    public static ProductResponse toProductResponse (Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .currentStock(product.getCurrentStock())
                .merchantName(product.getMerchantName())
                .merchantEmail(product.getMerchantEmail())
                .merchantPhoneNumber(product.getMerchantPhoneNumber())
                .build();
    }
}
