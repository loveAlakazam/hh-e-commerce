package io.hh_plus.ecommerce.ecommerce.domain.service.point.dto.request;

import io.hh_plus.ecommerce.ecommerce.domain.model.point.Point;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import lombok.*;

@Builder
@Getter
public class ChargePointServiceRequestDto {
    private final long userId;
    private final int amount;

    public ChargePointServiceRequestDto(long userId, int amount) {
        User.validateUserId(userId); // userId 유효성검증
        Point.validateChargeAmount(amount);// 충전금액(amount) 유효성검증

        this.userId = userId;
        this.amount = amount;
    }
}
