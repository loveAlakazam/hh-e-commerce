package io.hh_plus.ecommerce.ecommerce.domain.service.product.exception;

import com.sun.net.httpserver.HttpsServer;
import io.hh_plus.ecommerce.ecommerce.application.exceptions.IErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProductErrorCode implements IErrorCode {
    // 상품 정책 관련 에러
    PRODUCT_ID_POSITIVE_NUMBER_POLICY(HttpStatus.BAD_REQUEST, "상품 식별자는 양수여야 합니다."),

    // 상품 비즈니스 관련 에러
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않은 상품입니다.")
    ;


    // 에러 정의
    private final HttpStatus httpStatus;
    private String message;
    ProductErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getCode() {
        // Enum 객체의 이름을 리턴
        return this.name();
    }
}
