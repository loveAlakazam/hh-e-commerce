package io.hh_plus.ecommerce.ecommerce.domain.service.product;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.BusinessException;
import io.hh_plus.ecommerce.ecommerce.application.exceptions.InvalidRequestException;
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

import java.util.Optional;

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
}
