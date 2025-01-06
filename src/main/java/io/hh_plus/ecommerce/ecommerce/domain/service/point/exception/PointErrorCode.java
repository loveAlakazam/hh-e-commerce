package io.hh_plus.ecommerce.ecommerce.domain.service.point.exception;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.IErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum PointErrorCode implements IErrorCode {
    // 포인트 정책 관련 에러
    CHARGE_POINT_MINIMUM_POLICY( HttpStatus.BAD_REQUEST, "충전금액은 최소 1000원 이상이어야 합니다."),

    // 포인트 비즈니스 로직 관련 에러
    INSUFFICIENT_POINT(HttpStatus.BAD_REQUEST, "포인트가 부족합니다.")
    ;

    // 에러 정의
    private final HttpStatus httpStatus;
    private final String message;
    PointErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
