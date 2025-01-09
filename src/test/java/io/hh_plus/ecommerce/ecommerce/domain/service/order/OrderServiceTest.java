package io.hh_plus.ecommerce.ecommerce.domain.service.order;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.BusinessException;
import io.hh_plus.ecommerce.ecommerce.domain.model.order.Orderment;
import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderItemRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderServiceRequestDto;
import io.hh_plus.ecommerce.ecommerce.repository.order.OrderRepository;
import io.hh_plus.ecommerce.ecommerce.repository.product.ProductRepository;
import io.hh_plus.ecommerce.ecommerce.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("주문 유저가 존재하지 않으면 BusinessException 예외를 발생한다")
    void test_createOrderment_NotFound_User_shouldThrow_BusinessException() {
        // given
        long userId = 999L; // invalid: 존재하지 않은 유저 아이디
        long productId = 1L;

        CreateOrderItemRequestDto item = new CreateOrderItemRequestDto(productId, 1); // 주문항목- productId: 1 상품 1개
        CreateOrderServiceRequestDto requestDto = new CreateOrderServiceRequestDto(userId, Collections.singletonList(item));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // when
        assertThrows(BusinessException.class, () -> orderService.create(requestDto));

        // then
        verify(userRepository, times(1)).findById(userId);
        verify(productRepository, never()).findById(anyLong());
        verify(orderRepository, never()).save(any(Orderment.class));
    }
    @Test
    @DisplayName("상품이 존재하지 않으면 BusinessException 예외를 발생한다")
    void test_createOrderment_NotFound_Product_shouldThrow_BusinessException() {
        // given
        long userId = 1L;
        long productId = 999L; // invalid: 존재하지 않은 상품 아이디

        User user = new User();
        user.setId(userId);

        CreateOrderItemRequestDto item = new CreateOrderItemRequestDto(productId, 1); // 주문항목- productId: 1 상품 1개
        CreateOrderServiceRequestDto requestDto = new CreateOrderServiceRequestDto(userId, Collections.singletonList(item));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // when
        assertThrows(BusinessException.class, () -> orderService.create(requestDto));

        // then
        verify(userRepository, times(1)).findById(userId);
        verify(productRepository, times(1)).findById(productId);
        verify(orderRepository, never()).save(any(Orderment.class));
    }

    @Test
    @DisplayName("재고수량 < 주문수량 으로 재고부족 이면 BusinessException 예외를 발생한다")
    void test_createOrderment_currentStock_less_than_orderQuantity_shouldThrow_BusinessException() {
        // given
        long userId = 1L;
        long productId = 1L;

        User user = new User();
        user.setId(userId);
        Product product = new Product(
            productId,
            "상품1",
            15000,
            "설명1",
            0,
            "판매자1",
            "seller1@example.com",
            "01012345678"
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        CreateOrderItemRequestDto item = new CreateOrderItemRequestDto(productId, 1); // 주문항목- productId: 1 상품 1개
        CreateOrderServiceRequestDto requestDto = new CreateOrderServiceRequestDto(userId, Collections.singletonList(item));

        // when
        assertThrows(BusinessException.class, () -> orderService.create(requestDto));

        // then
        verify(userRepository, times(1)).findById(userId);
        verify(productRepository, times(1)).findById(productId);
        verify(orderRepository, never()).save(any(Orderment.class));
    }

    @Test
    @DisplayName("주문 생성 성공")
    void test_createOrderment_shouldReturn_success() {
        // given
        long userId = 1L;
        long productId1 = 101L;
        long productId2 = 102L;

        User user = new User();
        user.setId(userId);

        Product product1 = new Product(productId1, "상품1", 15000, "설명1", 10, "판매자1", "seller1@example.com", "01012345678");
        Product product2 = new Product(productId2, "상품2",  4300, "설명2", 5, "판매자2", "seller2@example.com", "01011112222");

        CreateOrderItemRequestDto item1 = new CreateOrderItemRequestDto(productId1, 1); // 주문항목- productId: 1 상품 1개
        CreateOrderItemRequestDto item2 = new CreateOrderItemRequestDto(productId2, 2); // 주문항목- productId: 2 상품 2개
        CreateOrderServiceRequestDto requestDto = new CreateOrderServiceRequestDto(userId, Arrays.asList(item1, item2));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId1)).thenReturn(Optional.of(product1));
        when(productRepository.findById(productId2)).thenReturn(Optional.of(product2));

        // when
        orderService.create(requestDto);

        // then
        verify(userRepository, times(1)).findById(userId);
        verify(productRepository, times(1)).findById(productId1);
        verify(productRepository, times(1)).findById(productId2);
        verify(orderRepository, times(1)).save(any(Orderment.class));
    }
}
