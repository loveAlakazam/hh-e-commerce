package io.hh_plus.ecommerce.ecommerce.domain.service.order;

import io.hh_plus.ecommerce.ecommerce.repository.order.OrderRepository;
import org.springframework.stereotype.Service;


public interface OrderService {
    // 주문을 생성한다
    public void create(long userId, long productId, int quantity, int price);

}
