package io.hh_plus.ecommerce.ecommerce.domain.service.product;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.BusinessException;
import io.hh_plus.ecommerce.ecommerce.application.exceptions.InvalidRequestException;
import io.hh_plus.ecommerce.ecommerce.domain.model.common.PageResponse;
import io.hh_plus.ecommerce.ecommerce.domain.model.product.Product;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.dto.response.ProductResponse;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.exception.ProductErrorCode;
import io.hh_plus.ecommerce.ecommerce.repository.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static io.hh_plus.ecommerce.ecommerce.domain.model.product.Product.PAGINATION_LIMIT;
import static io.hh_plus.ecommerce.ecommerce.domain.model.product.Product.validateProductId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    public class Products_ValidationCheck {
        @Test
        @DisplayName("상품 아이디는 0이하 음수면 InvalidRequestException 을 발생한다.")
        void test_validate_userId_not_positiveNumber_shouldThrow_InvalidRequestException () {
            // given
            long invalidProductId = -1L;

            // when
            InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> validateProductId(invalidProductId));

            // then
            assertEquals(ProductErrorCode.PRODUCT_ID_POSITIVE_NUMBER_POLICY.getMessage(), exception.getMessage());
        }

    }

    @Nested
    public class GetProductDetail_UnitTests{
        @Test
        @DisplayName("상품 아이디가 존재하지 않으면 BusinessException 을 발생한다")
        void test_getById_productId_does_not_exist_shouldThrow_BusinessException () {
            // given
            long invalidProductId = 99L; // invalid: 존재하지 않은 productId

            // when
            BusinessException exception = assertThrows(BusinessException.class, () -> productService.getById(invalidProductId));

            // then
            assertEquals(ProductErrorCode.PRODUCT_NOT_FOUND.getMessage(), exception.getMessage());
            verify(productRepository, times(1)).findById(invalidProductId);
        }
        @Test
        @DisplayName("상품 상세정보 조회를 성공한다")
        void test_getById_shouldSuccess () {
            // given
            long productId = 1L;
            Product mockProduct = new Product();
            mockProduct.setId(productId);
            mockProduct.setName("테스트 아이템1");

            when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

            // when
            ProductResponse productDetail = productService.getById(productId);

            // then
            assertEquals(mockProduct.getName(), productDetail.getName());
            assertEquals(mockProduct.getId(), productDetail.getId());
            verify(productRepository, times(1)).findById(productId);
        }
    }

    @Nested
    public class GetProducts_UnitTest {
        @Test
        @DisplayName("상품데이터가 존재하지 않으면 빈 리스트로 응답한다")
        void test_getProducts_shouldReturn_empty_list () {
            // given
            int page = 1;
            int offset = page -1;
            Pageable pageable = PageRequest.of(offset, PAGINATION_LIMIT);
            when(productRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.emptyList(), pageable, 0));

            // when
            PageResponse<ProductResponse> response = productService.getProducts(page);

            // then
            assertEquals(0, response.getContent().size()); // 페이지당 나타내는 상품리스트
            assertEquals(0, response.getTotalElements()); // 전체 상품 개수
            assertEquals(0, response.getTotalPages()); // 전체 상품 토탈 페이징수
        }
        @Test
        @DisplayName("상품데이터 10개이상 존재할 경우 1 페이지의 10개 데이터 리스트로 응답한다")
        void test_getProducts_firstPage_shouldReturn_list () {
            // given
            int page = 1;
            int offset = page -1;
            Pageable pageable = PageRequest.of(offset, PAGINATION_LIMIT);

            // 상품결과를 10개를 나타냄.
            List<Product> mockProducts = IntStream.range(1, 11)
                    .mapToObj(i -> new Product(
                            (long)i,
                            "상품 "+i,
                            1000 * i,
                            "상품 "+i+" 설명",
                            10,
                            "판매자",
                            "seller@example.com",
                            "01012345678"
                    )).toList();

            // 전체 데이터개수(total) 20개 중 상품결과 10개를 나타내도록 mocking
            when(productRepository.findAll(pageable)).thenReturn(new PageImpl<>(mockProducts, pageable, 20));

            // when
            PageResponse<ProductResponse> response = productService.getProducts(page);

            // then
            assertEquals(10, response.getContent().size()); // 1페이지의 상품개수
            assertEquals(20, response.getTotalElements()); // 전체 상품개수 20개
            assertEquals(2, response.getTotalPages()); // 전체 페이지 개수
        }
        @Test
        @DisplayName("상품 데이터 10개이상 존재할 경우 2페이지의 10개 데이터 리스트로 응답한다")
        void test_getProducts_secondPage_shouldReturn_list () {
            // given
            int page = 2;
            int offset = page -1;
            Pageable pageable = PageRequest.of(offset, PAGINATION_LIMIT);

            // 상품결과를 10개를 나타냄.
            List<Product> mockProducts = IntStream.range(11, 21)
                    .mapToObj(i -> new Product(
                            (long) i,
                            "상품 "+i,
                            1000 * i,
                            "상품"+i+" 설명",
                            10,
                            "판매자",
                            "seller@example.com",
                            "01012345678"
                    )).toList();

            // 전체 데이터개수(total) 20개 중 상품결과 10개를 나타내도록 mocking
            when(productRepository.findAll(pageable)).thenReturn(new PageImpl<>(mockProducts, pageable, 20));

            // when
            PageResponse<ProductResponse> response = productService.getProducts(page);

            // then
            assertEquals(10, response.getContent().size());
            assertEquals(20, response.getTotalElements());
            assertEquals(2, response.getTotalPages());
        }
    }
}
