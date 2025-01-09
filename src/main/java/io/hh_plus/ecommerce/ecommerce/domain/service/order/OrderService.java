package io.hh_plus.ecommerce.ecommerce.domain.service.order;

import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderItemServiceRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderServiceRequestDto;
import io.hh_plus.ecommerce.ecommerce.repository.order.OrderRepository;
import org.springframework.stereotype.Service;


public interface OrderService {
    // 주문을 생성한다
    public void create(CreateOrderServiceRequestDto requestDto);

}
