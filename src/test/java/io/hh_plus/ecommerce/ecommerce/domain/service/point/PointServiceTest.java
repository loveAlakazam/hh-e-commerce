package io.hh_plus.ecommerce.ecommerce.domain.service.point;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.BusinessException;
import io.hh_plus.ecommerce.ecommerce.application.exceptions.InvalidRequestException;
import io.hh_plus.ecommerce.ecommerce.domain.model.point.Point;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import io.hh_plus.ecommerce.ecommerce.domain.service.point.dto.request.ChargePointServiceRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.point.exception.PointErrorCode;
import io.hh_plus.ecommerce.ecommerce.domain.service.user.exception.UserErrorCode;
import io.hh_plus.ecommerce.ecommerce.repository.point.PointRepository;
import io.hh_plus.ecommerce.ecommerce.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class PointServiceTest {

    @Mock
    private UserRepository userRepository; // 테스트 대상

    @Mock
    private PointRepository pointRepository; // MockRepository

    @InjectMocks
    private PointServiceImpl pointService; // 테스트하려는 서비스

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mock 초기화
    }


    @Nested
    public class ChargeAmount_UnitTests_ValidationCheck {
        /* 유효성 검증 실패 테스트 케이스 */
        @Test
        @DisplayName("유저 아이디가 0이하의 음의정수이면 InvalidRequestException 을 발생한다")
        void test_chargePoint_userId_not_positive_number_shouldThrow_test_InvalidRequestException() {
            // given
            long userId = -1L; // invalid : 0이하의 음의 정수 userId
            int chargeAmount = 1000;

            // when
            InvalidRequestException exception = assertThrows(InvalidRequestException.class, () ->  new ChargePointServiceRequestDto(userId, chargeAmount));

            // then
            assertEquals(UserErrorCode.USER_ID_IS_POSITIVE_NUMBER_POLICY.getMessage(), exception.getMessage());
        }
        @Test
        @DisplayName("1000원 미만의 금액을 충전하면 InvalidRequestException 을 발생한다")
        void test_chargePoint_amount_under_1000_shouldThrow_InvalidRequestException() {
            // given
            long userId = 1L;
            int invalidChargeAmount = 500;  // invalid:  1000 원 미만

            // when
            // - ChargePointServiceRequestDto 객체를 생성했을 때 validation 오류를 발생.
            // - 생성자 호출할때 validation 을 실행하고, validation 오류의 책임을 dto 생성자에게 맡김.
            InvalidRequestException exception = assertThrows(InvalidRequestException.class, () ->  new ChargePointServiceRequestDto(userId, invalidChargeAmount));

            // then
            assertEquals(PointErrorCode.CHARGE_POINT_MINIMUM_POLICY.getMessage(), exception.getMessage());
        }
        /* 유효성 검증 성공 테스트 케이스 */
        @Test
        @DisplayName("chargeAmount() 의 input Data 에 관한 유효성검사를 성공한다.")
        void test_chargePoint_shouldReturn_Success() {
            // given
            long userId = 1L;
            int chargeAmount = 1000;

            // when
            ChargePointServiceRequestDto requestDto = new ChargePointServiceRequestDto(userId, chargeAmount);

            // then
            assertEquals(userId, requestDto.getUserId());
            assertEquals(chargeAmount, requestDto.getAmount());
            assertInstanceOf(ChargePointServiceRequestDto.class, requestDto);
        }
    }
    @Nested
    public class ChargeAmount_UnitTests_BusinessLogics {
        @Test
        @DisplayName("userId 가 존재하지 않으면 포인트 조회를 할 수 없으므로 BusinessException 을 발생한다.")
        void test_chargePoint_userId_does_not_exists_shouldThrow_BusinessException() {
            // given
            long userId = 999L; // invalid: 존재하지 않은 유저 ID
            int chargeAmount = 1000;
            ChargePointServiceRequestDto requestDto = new ChargePointServiceRequestDto(userId, chargeAmount);

            // when
            BusinessException exception = assertThrows(BusinessException.class, () -> pointService.chargePoint(requestDto));

            // then
            assertEquals(UserErrorCode.USER_NOT_FOUND.getMessage(), exception.getMessage());
            verify(pointRepository, times(1)).findByUserId(userId); // findByUserId 1번 호출
            verify(pointRepository, times(0)).save(any(Point.class)); // save 호출하지 않음
        }
        @Test
        @DisplayName("포인트 충전에 성공한다")
        void test_chargePoint_shouldReturn_success() {
            // given
            long userId = 1L; // invalid: 존재하지 않은 유저 ID
            int currentPoint = 5000;
            int chargeAmount = 1000;

            User mockUser = new User(); // 가짜 유저객체 정의
            mockUser.setId(userId);

            Point mockPoint = new Point(); // 가짜 Point 객체 정의
            mockPoint.setId(1L);
            mockPoint.setValue(currentPoint);
            mockPoint.setUser(mockUser);

            ChargePointServiceRequestDto requestDto = new ChargePointServiceRequestDto(userId, chargeAmount);
            when(pointRepository.findByUserId(userId)).thenReturn(Optional.of(mockPoint));

            // when
            pointService.chargePoint(requestDto);

            // then
            verify(pointRepository, times(1)).findByUserId(userId);
            verify(pointRepository, times(1)).save(mockPoint);
            assertEquals(currentPoint + chargeAmount, mockPoint.getValue());
        }
    }

    @Nested
    public class GetCurrentPointUnitTests {
        @Test
        @DisplayName("userId 가 존재하지 않으면 포인트 조회를 할 수 없으므로 BusinessException 을 발생한다.")
        void test_chargePoint_userId_does_not_exists_shouldThrow_BusinessException() {
            // given
            long userId = 999L; // invalid: 존재하지 않은 유저 ID

            // when
            BusinessException exception = assertThrows(BusinessException.class, () -> pointService.getCurrentPoint(userId));

            // then
            assertEquals(UserErrorCode.USER_NOT_FOUND.getMessage(), exception.getMessage());
            verify(pointRepository, times(1)).findByUserId(userId); // findByUserId 1번 호출
            verify(pointRepository, times(0)).save(any(Point.class)); // save 호출하지 않음
        }
    }
}
