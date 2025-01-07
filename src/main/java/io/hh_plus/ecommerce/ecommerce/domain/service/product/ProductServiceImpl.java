package io.hh_plus.ecommerce.ecommerce.domain.service.product;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.BusinessException;
import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.dto.response.ProductResponse;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.exception.ProductErrorCode;
import io.hh_plus.ecommerce.ecommerce.mapper.product.ProductMapper;
import io.hh_plus.ecommerce.ecommerce.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.hh_plus.ecommerce.ecommerce.domain.model.product.Product.validateProductId;

@Service
public class ProductServiceImpl implements  ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse getById(long productId) {
        validateProductId(productId);

        return productRepository.findById(productId)
                .map(ProductMapper::toProductResponse)
                .orElseThrow(() -> new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND));
    }
}
