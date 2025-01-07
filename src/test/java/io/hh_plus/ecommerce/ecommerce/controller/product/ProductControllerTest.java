package io.hh_plus.ecommerce.ecommerce.controller.product;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.GlobalExceptionHandler;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.ProductService;
import io.hh_plus.ecommerce.ecommerce.domain.service.product.dto.response.ProductResponse;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("상품 productId 상세조회 API 성공케이스")
    void test_getProductDetail_success() throws Exception {
        // given
        long productId = 1L;

        ProductResponse mockProductResponse = ProductResponse.builder()
                .id(productId)
                .name("테스트 상품")
                .price(10000)
                .description("테스트 상품 설명")
                .currentStock(50)
                .merchantName("테스트 판매자")
                .merchantEmail("seller@example.com")
                .merchantPhoneNumber("010-1234-5678")
                .build();

        when(productService.getById(productId)).thenReturn(mockProductResponse);

        // when
        MvcResult result = mockMvc.perform(get("/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        ProductResponse product = objectMapper.readValue(responseContent, ProductResponse.class);
        // then
        assertEquals(mockProductResponse.getId(), product.getId());
        assertEquals(mockProductResponse.getName(), product.getName());
        assertEquals(mockProductResponse.getPrice(), product.getPrice());
        verify(productService, times(1)).getById(productId);
    }
}
