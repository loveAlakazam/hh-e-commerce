package io.hh_plus.ecommerce.ecommerce.domain.service.product;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.BusinessException;
import io.hh_plus.ecommerce.ecommerce.domain.model.common.PageResponse;
import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.dto.response.ProductResponse;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.exception.ProductErrorCode;
import io.hh_plus.ecommerce.ecommerce.mapper.product.ProductMapper;
import io.hh_plus.ecommerce.ecommerce.repository.product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static io.hh_plus.ecommerce.ecommerce.domain.model.product.Product.PAGINATION_LIMIT;
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

    @Override
    public PageResponse<ProductResponse> getProducts(int page) {
        int offset = page -1;
        Pageable pageable = PageRequest.of( offset, PAGINATION_LIMIT );
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponse> products = productPage.getContent().stream()
                .map(ProductMapper::toProductResponse)
                .toList();

        return new PageResponse<>(
            products,
            productPage.getTotalElements(),
            productPage.getTotalPages(),
            offset,
            PAGINATION_LIMIT
        );
    }
}
