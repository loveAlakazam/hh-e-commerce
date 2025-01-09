package io.hh_plus.ecommerce.ecommerce.domain.service.order.exception;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.IErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum OrderErrorCode implements IErrorCode {
    // 주문 정책 관련 에러
    ORDER_TOTAL_QUANTITY_IS_POSITIVE_NUMBER_POLICY(HttpStatus.BAD_REQUEST, "총 주문수량은 최소 1개 이상이어야 합니다."),
    ORDER_ORIGIN_TOTAL_PRICE_IS_POSITIVE_NUMBER_POLICY(HttpStatus.BAD_REQUEST, "총 주문금액은 ")
    // 주문 비즈니스 로직 관련 에러
    ;

    private final HttpStatus httpStatus;
    private final String message;

    OrderErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
