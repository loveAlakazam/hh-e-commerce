package io.hh_plus.ecommerce.ecommerce.domain.service.point;


import io.hh_plus.ecommerce.ecommerce.domain.service.point.dto.request.ChargePointServiceRequestDto;

public interface PointService {
    // 잔액 충전
    public void chargePoint(ChargePointServiceRequestDto dto);

    // 잔액 조회
    public Integer getCurrentPoint(long userId);
}
