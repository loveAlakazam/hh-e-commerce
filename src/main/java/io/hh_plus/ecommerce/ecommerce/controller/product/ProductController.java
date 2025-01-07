package io.hh_plus.ecommerce.ecommerce.controller.product;

import io.hh_plus.ecommerce.ecommerce.domain.service.product.ProductService;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 목록 조회 API
    @GetMapping()
    public ResponseEntity<String> getProducts() {
        return ResponseEntity.ok("상품목록 조회");
    }


    // 상품 단품 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductDetail(@PathVariable long id) {
        return ResponseEntity.ok(productService.getById(id));
    }


    // 상위 상품 조회 API
    @GetMapping("/best5")
    public ResponseEntity<String> getBestSellerProduct() {
        return ResponseEntity.ok("가장 많이 팔린 상위5개 상품 조회");
    }

}
