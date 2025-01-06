package io.hh_plus.ecommerce.ecommerce.domain.service.point;

import io.hh_plus.ecommerce.ecommerce.repository.point.PointRepository;
import io.hh_plus.ecommerce.ecommerce.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PointServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PointRepository pointRepository;

    @InjectMocks
    private PointService pointService;


    @Nested
    public class ChargePointUnitTests {
        @Test
        @DisplayName("1000원 미만의 금액을 충전하면 ")
        void testChargePoint() {

        }

    }

    @Nested
    public class GetCurrentPointUnitTests {

    }
}
