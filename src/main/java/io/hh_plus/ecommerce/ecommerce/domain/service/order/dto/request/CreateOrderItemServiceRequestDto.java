package io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemServiceRequestDto {
    private long userId;
    private List<CreateOrderItemRequestDto> orderItemList;
}
