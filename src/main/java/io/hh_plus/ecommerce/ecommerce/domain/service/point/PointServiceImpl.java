package io.hh_plus.ecommerce.ecommerce.domain.service.point;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.BusinessException;
import io.hh_plus.ecommerce.ecommerce.domain.model.point.Point;
import io.hh_plus.ecommerce.ecommerce.domain.service.point.dto.request.ChargePointServiceRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.user.exception.UserErrorCode;
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
    public void chargePoint(ChargePointServiceRequestDto requestDto) {
        long userId = requestDto.getUserId();
        int amount = requestDto.getAmount();

        Point userCurrentPoint = pointRepository.findByUserId(userId).orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        // 충전후 금액 = 현재 포인트양 + 충전포인트양(point)
        int chargePoint = userCurrentPoint.getValue() + amount;
        userCurrentPoint.setValue(chargePoint);

        // 데이터 업데이트
        pointRepository.save(userCurrentPoint);
    }

    // 잔액조회
    @Override
    public Integer getCurrentPoint(long userId) {
        Point point = pointRepository.findByUserId(userId).orElseThrow(()-> new BusinessException(UserErrorCode.USER_NOT_FOUND));
        return point.getValue();
    }
}
