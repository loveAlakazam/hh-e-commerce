package io.hh_plus.ecommerce.ecommerce.domain.service.product;

import io.hh_plus.ecommerce.ecommerce.domain.service.product.dto.response.ProductResponse;

import java.util.Optional;

public interface ProductService {
    ProductResponse getById(long productId);
}
