package io.hh_plus.ecommerce.ecommerce.domain.service.user;

import org.springframework.stereotype.Service;


public interface UserService {
    // 잔액 충전
    public void addPoint(long userId, int point);

    // 잔액 조회
    public void getPoint(long userId);
}
