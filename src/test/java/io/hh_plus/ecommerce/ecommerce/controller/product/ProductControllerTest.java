package io.hh_plus.ecommerce.ecommerce.controller.product;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.GlobalExceptionHandler;
import io.hh_plus.ecommerce.ecommerce.domain.model.common.PageResponse;
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

import java.util.List;
import java.util.stream.IntStream;

import static io.hh_plus.ecommerce.ecommerce.domain.model.product.Product.PAGINATION_LIMIT;
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

    @Test
    @DisplayName("상품 목록 조회 API - 기본페이지(1 페이지) 성공")
    void test_getProducts_success() throws Exception {
        // given
        int page = 1;
        int offset = page -1;
        List<ProductResponse> mockProducts = IntStream.range(1, 11)
                .mapToObj(i -> ProductResponse.builder()
                        .id((long) i)
                        .name("상품 " + i)
                        .price(1000 * i)
                        .description("설명 " + i)
                        .currentStock(10)
                        .merchantName("판매자")
                        .merchantEmail("seller@example.com")
                        .merchantPhoneNumber("010-1234-5678")
                        .build())
                .toList();

        PageResponse<ProductResponse> mockResponse = new PageResponse<>(
            mockProducts, 20, 2, offset, PAGINATION_LIMIT
        );
        when(productService.getProducts(page)).thenReturn(mockResponse);

        // when
        MvcResult result = mockMvc.perform(get("/products")
                .param("page", String.valueOf(page))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        String responseContent = result.getResponse().getContentAsString();
        PageResponse actualResponse = new ObjectMapper().readValue(responseContent, PageResponse.class);

        assertEquals(mockResponse.getContent().size(), actualResponse.getContent().size());
        assertEquals(mockResponse.getTotalElements(), actualResponse.getTotalElements());
        assertEquals(mockResponse.getTotalPages(), actualResponse.getTotalPages());

        verify(productService, times(1)).getProducts(page);
    }
    @Test
    @DisplayName("상품 목록 조회 API - 2페이지 성공")
    void test_getProducts_secondPage_success() throws Exception {
        // given
        int page = 2;
        List<ProductResponse> mockProducts = IntStream.range(11, 21)
                .mapToObj(i -> ProductResponse.builder()
                        .id((long) i)
                        .name("상품 " + i)
                        .price(1000 * i)
                        .description("설명 " + i)
                        .currentStock(10)
                        .merchantName("판매자")
                        .merchantEmail("seller@example.com")
                        .merchantPhoneNumber("010-1234-5678")
                        .build())
                .toList();

        PageResponse<ProductResponse> mockResponse = new PageResponse<>(
                mockProducts, 20, 2, page - 1, PAGINATION_LIMIT
        );

        when(productService.getProducts(page)).thenReturn(mockResponse);

        // when
        MvcResult result = mockMvc.perform(get("/products")
                        .param("page", String.valueOf(page))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expect 200
                .andReturn();

        // then
        String responseContent = result.getResponse().getContentAsString();
        PageResponse<ProductResponse> actualResponse = new ObjectMapper().readValue(responseContent, PageResponse.class);

        assertEquals(mockResponse.getContent().size(), actualResponse.getContent().size());
        assertEquals(mockResponse.getTotalElements(), actualResponse.getTotalElements());
        assertEquals(mockResponse.getTotalPages(), actualResponse.getTotalPages());

        verify(productService, times(1)).getProducts(page);
    }

}
