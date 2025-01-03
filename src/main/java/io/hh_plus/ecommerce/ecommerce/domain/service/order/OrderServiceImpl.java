package io.hh_plus.ecommerce.ecommerce.domain.service.order;

import io.hh_plus.ecommerce.ecommerce.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements  OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void create(long userId, long productId, int quantity, int price) {

    }
}
