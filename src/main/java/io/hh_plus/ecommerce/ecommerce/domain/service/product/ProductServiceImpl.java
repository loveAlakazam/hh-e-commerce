package io.hh_plus.ecommerce.ecommerce.domain.service.product;

import io.hh_plus.ecommerce.ecommerce.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements  ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void getById(long productId) {

    }
}
