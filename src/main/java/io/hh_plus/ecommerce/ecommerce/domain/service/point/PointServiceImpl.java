package io.hh_plus.ecommerce.ecommerce.domain.service.point;

import io.hh_plus.ecommerce.ecommerce.domain.model.point.Point;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import io.hh_plus.ecommerce.ecommerce.repository.point.PointRepository;
import io.hh_plus.ecommerce.ecommerce.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    public PointServiceImpl(UserRepository userRepository, PointRepository pointRepository) {
        this.userRepository = userRepository;
        this.pointRepository = pointRepository;
    }

    // 잔액충전
    @Override
    public void chargePoint(long userId, int point) {
        if(point <= 0)
            throw new IllegalArgumentException("충전 금액은 0보다 커야합니다.");

        Point userCurrentPoint = pointRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 충전후 금액 = 현재 포인트양 + 충전포인트양(point)
        int chargePoint = userCurrentPoint.getValue() + point;
        userCurrentPoint.setValue(chargePoint);

        // 데이터 업데이트
        pointRepository.save(userCurrentPoint);
    }

    // 잔액조회
    @Override
    public Integer getCurrentPoint(long userId) {
        Point point = pointRepository.findByUserId(userId).orElseThrow(()-> new IllegalArgumentException("존재하지 않은 유저입니다."));
        return point.getValue();
    }
}
