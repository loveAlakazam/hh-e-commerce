package io.hh_plus.ecommerce.ecommerce.controller.point;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.BusinessException;
import io.hh_plus.ecommerce.ecommerce.application.exceptions.GlobalExceptionHandler;
import io.hh_plus.ecommerce.ecommerce.controller.point.dto.request.ChargePointRequest;
import io.hh_plus.ecommerce.ecommerce.domain.model.point.Point;
import io.hh_plus.ecommerce.ecommerce.domain.service.point.PointService;
import io.hh_plus.ecommerce.ecommerce.domain.service.point.dto.request.ChargePointServiceRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.user.exception.UserErrorCode;
import io.hh_plus.ecommerce.ecommerce.repository.point.PointRepository;
import io.hh_plus.ecommerce.ecommerce.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private PointService pointService;

    @Mock
    private PointRepository pointRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PointController pointController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pointController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("포인트 충전 API 성공케이스")
    void test_chargePoint_success() throws Exception {
        // given
        long userId = 1L;
        int chargePoint = 2000;

        ChargePointRequest request = new ChargePointRequest(chargePoint);

        doNothing().when(pointService).chargePoint(any(ChargePointServiceRequestDto.class)); // 서비스로직 chargePoint mocking

        // when
        mockMvc.perform(put("/points/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(pointService, times(1)).chargePoint(any(ChargePointServiceRequestDto.class));
    }

    @Test
    @DisplayName("포인트 충전 실패 API - 유저식별자에 대응되는 포인트를 찾을 수 없음")
    void test_chargePoint_failed_NotFoundUser() throws Exception {
        // given
        long userId = 999L; // invalid: does not exist
        int chargePoint = 2000;
        ChargePointRequest request = new ChargePointRequest(chargePoint);

        // pointService.chargePoint 호출 시 예외 발생 설정
        doThrow(new BusinessException(UserErrorCode.USER_NOT_FOUND))
                .when(pointService)
                .chargePoint(any(ChargePointServiceRequestDto.class));

        // when
        MvcResult result = mockMvc.perform(put("/points/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andReturn();

        // then
        String responseContent = result.getResponse().getContentAsString();
        assertTrue(responseContent.contains(UserErrorCode.USER_NOT_FOUND.getMessage()));
        verify(pointService, times(1)).chargePoint(any(ChargePointServiceRequestDto.class));
    }

    @Test
    @DisplayName("포인트 조회 API 성공 케이스")
    void test_getPoint_success() throws Exception {
        // given
        long userId = 1L;
        int currentPoint = 2000;

        when(pointService.getCurrentPoint(userId)).thenReturn(currentPoint);

        // when
        MvcResult result = mockMvc.perform(get("/points/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // 응답 본문 검증
        String responseContent = result.getResponse().getContentAsString();
        assertEquals(String.valueOf(currentPoint), responseContent);

        // then
        verify(pointService, times(1)).getCurrentPoint(userId);
    }
}
