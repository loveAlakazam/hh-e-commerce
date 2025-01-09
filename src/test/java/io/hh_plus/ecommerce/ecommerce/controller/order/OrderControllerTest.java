package io.hh_plus.ecommerce.ecommerce.controller.order;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.GlobalExceptionHandler;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.OrderService;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderItemRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("주문생성 API 성공케이스")
    void test_createOrder_success() throws Exception {
        // given
        CreateOrderRequestDto requestBody = new CreateOrderRequestDto(1L, Arrays.asList(
            new CreateOrderItemRequestDto(10L, 1),
            new CreateOrderItemRequestDto(11L, 2)
        ));
        doNothing().when(orderService).create(any(CreateOrderRequestDto.class));

        // when & then
        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody))
        ).andExpect(status().isOk());

        verify(orderService, times(1)).create(any(CreateOrderRequestDto.class));
    }


}

