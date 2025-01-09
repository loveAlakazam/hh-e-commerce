package io.hh_plus.ecommerce.ecommerce.domain.service.order;

import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.response.OrderResponse;

import java.util.Optional;


public interface OrderService {
    // 주문을 생성한다
    void create(CreateOrderRequestDto requestDto);

    // 주문 아이디로 주문을 조회한다
    Optional<OrderResponse> getOrderById(long id);

}
