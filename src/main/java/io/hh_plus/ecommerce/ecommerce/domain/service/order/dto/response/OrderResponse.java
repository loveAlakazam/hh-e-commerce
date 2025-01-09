package io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.response;

import io.hh_plus.ecommerce.ecommerce.domain.model.order.OrderItem;
import io.hh_plus.ecommerce.ecommerce.domain.model.order.PaymentStatus;
import io.hh_plus.ecommerce.ecommerce.domain.model.payment.Payment;
import io.hh_plus.ecommerce.ecommerce.domain.service.payment.dto.response.PaymentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private long id;
    private int totalQuantity;
    private int totalPrice;
    private PaymentStatus paymentStatus;
    private PaymentResponse payment;
    private List<OrderItemResponse> orderItems;
}
