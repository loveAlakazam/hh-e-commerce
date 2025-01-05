package io.hh_plus.ecommerce.ecommerce.repository.product;

import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
}
