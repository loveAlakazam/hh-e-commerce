package io.hh_plus.ecommerce.ecommerce.domain.service.point;



public interface PointService {
    // 잔액 충전
    public void chargePoint(long userId, int point);

    // 잔액 조회
    public Integer getCurrentPoint(long userId);
}
