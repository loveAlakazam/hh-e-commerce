package io.hh_plus.ecommerce.ecommerce.mapper.order;

import io.hh_plus.ecommerce.ecommerce.domain.model.order.OrderItem;
import io.hh_plus.ecommerce.ecommerce.domain.model.order.Orderment;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.response.OrderItemResponse;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.response.OrderResponse;

import java.util.Collections;
import java.util.List;

import static io.hh_plus.ecommerce.ecommerce.mapper.payment.PaymentMapper.toPaymentResponse;

public class OrderMapper {
    public static OrderResponse toOrderResponse (Orderment order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalQuantity(order.getQuantity())
                .totalPrice(order.getPrice())
                .paymentStatus(order.getPaymentStatus())
                .payment(toPaymentResponse(order.getPayment()))
                .orderItems(toOrderItemResponse(order.getOrderItems()))
                .build();
    }

    private static List<OrderItemResponse> toOrderItemResponse (List<OrderItem> orderItems) {
        if(orderItems == null || orderItems.isEmpty()) return Collections.emptyList();

        return orderItems.stream().map(item -> OrderItemResponse
                .builder()
                .id(item.getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .productName(item.getProduct().getName())
                .build()).toList();
    }
}
