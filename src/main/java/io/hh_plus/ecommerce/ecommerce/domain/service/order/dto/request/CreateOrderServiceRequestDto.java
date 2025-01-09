package io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request;

import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderServiceRequestDto {
    private long userId;
    private List<CreateOrderItemRequestDto> orderItems;

    public CreateOrderServiceRequestDto(long userId, List<CreateOrderItemRequestDto> orderItems) {
        User.validateUserId(userId); // userId 유효성 검증

        this.userId = userId;
        this.orderItems = orderItems;
    }
}
