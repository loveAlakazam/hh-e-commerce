package io.hh_plus.ecommerce.ecommerce.domain.service.order;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.BusinessException;
import io.hh_plus.ecommerce.ecommerce.domain.model.order.OrderItem;
import io.hh_plus.ecommerce.ecommerce.domain.model.order.Orderment;
import io.hh_plus.ecommerce.ecommerce.domain.model.order.PaymentStatus;
import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import io.hh_plus.ecommerce.ecommerce.domain.model.user.User;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderItemRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderItemServiceRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.request.CreateOrderRequestDto;
import io.hh_plus.ecommerce.ecommerce.domain.service.order.dto.response.OrderResponse;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.exception.ProductErrorCode;
import io.hh_plus.ecommerce.ecommerce.domain.service.user.exception.UserErrorCode;
import io.hh_plus.ecommerce.ecommerce.mapper.order.OrderMapper;
import io.hh_plus.ecommerce.ecommerce.repository.order.OrderRepository;
import io.hh_plus.ecommerce.ecommerce.repository.product.ProductRepository;
import io.hh_plus.ecommerce.ecommerce.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements  OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void create(CreateOrderRequestDto requestDto) {
        // 주문 요청 유저 아이디를 검증한다.
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        // 주문 생성
        Orderment order = new Orderment();
        order.setUser(user);
        order.setPaymentStatus(PaymentStatus.PENDING);

        // 요청 유저가 주문한 주문아이템 리스트를 찾는다.
        List<CreateOrderItemRequestDto> orderItemsRequest = requestDto.getOrderItems();
        List<OrderItem> orderItems = new ArrayList<>();
        int totalQuantity = 0;
        int totalPrice = 0;

        // 주문아이템의 상품이 실제로 존재하는지 확인하기.
        // - 주문아이템 리스트 요소의 구성요소는 '주문 아이템 아이디', '상품아이디', '상품가격', '상품개수' 로 구성되어있다.
        for(CreateOrderItemRequestDto itemRequest : orderItemsRequest) {
            // 상품 검증
            // - 만일 주문아이템중 존재하지 않은 상품이 발생시, BusinessException 에러 발생
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND));

            // 재고수량 < 주문수량
            if(product.getCurrentStock() < itemRequest.getQuantity())
                throw new BusinessException(ProductErrorCode.PRODUCT_LACK_OF_STOCK_POLICY);

            // 주문 아이템 생성
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice() * itemRequest.getQuantity());
            orderItem.setOrder(order); // 주문객체와 연결
            orderItems.add(orderItem);

            // 총수량 & 총주문개수
            totalQuantity += orderItem.getQuantity();
            totalPrice += orderItem.getPrice();
        }

        Orderment.validateTotalQuantity(totalQuantity); // totalQuantity 검증
        Orderment.validateTotalPrice(totalPrice); // totalPrice 검증

        // 주문 최종 설정 및 저장
        order.setPrice(totalPrice);
        order.setQuantity(totalQuantity);
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }

    @Override
    public Optional<OrderResponse> getOrderById(long id) {
        return orderRepository.findById(id).map(OrderMapper::toOrderResponse);
    }
}
