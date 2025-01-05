package io.hh_plus.ecommerce.ecommerce.repository.order;

import io.hh_plus.ecommerce.ecommerce.domain.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}