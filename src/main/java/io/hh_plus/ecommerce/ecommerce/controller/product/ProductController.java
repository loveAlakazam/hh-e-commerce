package io.hh_plus.ecommerce.ecommerce.controller.product;

import io.hh_plus.ecommerce.ecommerce.domain.service.product.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

}
