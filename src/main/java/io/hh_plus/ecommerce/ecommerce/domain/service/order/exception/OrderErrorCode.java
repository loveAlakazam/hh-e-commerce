package io.hh_plus.ecommerce.ecommerce.domain.service.order.exception;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.IErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static io.hh_plus.ecommerce.ecommerce.domain.model.order.Orderment.MINIMUM_TOTAL_PRICE;

@Getter
public enum OrderErrorCode implements IErrorCode {
    // 주문 정책 관련 에러
    ORDER_TOTAL_QUANTITY_IS_POSITIVE_NUMBER_POLICY(HttpStatus.BAD_REQUEST, "총 주문수량은 최소 1개 이상이어야 합니다."),
    ORDER_TOTAL_PRICE_IS_MORE_THAN_MINIMUM_TOTAL_PRICE_POLICY(HttpStatus.BAD_REQUEST, "총 주문금액은 "+MINIMUM_TOTAL_PRICE+"원 이상이어야 합니다."),
    // 주문 비즈니스 로직 관련 에러
    ORDER_NOT_FOUND (HttpStatus.NOT_FOUND, "존재하지 않은 주문입니다.")
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
