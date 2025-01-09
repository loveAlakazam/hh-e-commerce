package io.hh_plus.ecommerce.ecommerce.controller.order;

import io.hh_plus.ecommerce.ecommerce.domain.service.order.OrderService;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController( OrderService orderService) {
        this.orderService = orderService;
    }

    // 주문 생성 API
    @PostMapping()
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequestDto request) {
        orderService.create(request);
        return ResponseEntity.ok("주문완료");
    }

    // 주문 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderResponse>> getOrderById(@PathVariable long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
