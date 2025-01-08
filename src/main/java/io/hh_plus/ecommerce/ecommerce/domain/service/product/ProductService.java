package io.hh_plus.ecommerce.ecommerce.domain.service.product;

import io.hh_plus.ecommerce.ecommerce.domain.model.common.PageResponse;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.dto.response.ProductResponse;

import java.util.Optional;

public interface ProductService {
    ProductResponse getById(long productId);
    PageResponse<ProductResponse> getProducts(int page);
}
