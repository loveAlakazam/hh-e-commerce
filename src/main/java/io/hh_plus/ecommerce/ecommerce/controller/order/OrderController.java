package io.hh_plus.ecommerce.ecommerce.controller.order;

import io.hh_plus.ecommerce.ecommerce.domain.service.order.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController( OrderService orderService) {
        this.orderService = orderService;
    }
}
