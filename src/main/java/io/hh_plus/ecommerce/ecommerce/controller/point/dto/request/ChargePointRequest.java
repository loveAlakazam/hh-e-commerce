package io.hh_plus.ecommerce.ecommerce.controller.point.dto.request;

import io.hh_plus.ecommerce.ecommerce.domain.model.point.Point;
import lombok.*;

@Getter
public class ChargePointRequest {
    private int amount;

    public ChargePointRequest(int amount) {
        this.amount = amount;
    }
}
