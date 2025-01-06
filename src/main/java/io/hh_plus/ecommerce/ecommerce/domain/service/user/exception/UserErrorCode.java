package io.hh_plus.ecommerce.ecommerce.domain.service.user.exception;

import io.hh_plus.ecommerce.ecommerce.application.exceptions.IErrorCode;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import static java.util.prefs.Preferences.MAX_NAME_LENGTH;

@Getter
public enum UserErrorCode implements IErrorCode {
    // 유저 정책 관련 에러
    USER_ID_IS_POSITIVE_NUMBER_POLICY(HttpStatus.BAD_REQUEST, "유저 식별자는 양수 여야합니다."),
    USER_NAME_IS_LONGER_THAN_MINIMUM_LENGTH_SIZE_POLICY(HttpStatus.BAD_REQUEST, "유저 이름은 최대 "+ MAX_NAME_LENGTH +"자 까지 가능합니다."),
    USER_NAME_IS_REQUIRED_POLICY(HttpStatus.BAD_REQUEST, "유저 이름은 필수 입니다."),


    // 유저 비즈니스 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않은 유저입니다.")
    ;

    // 에러 정의
    private final HttpStatus httpStatus;
    private final String message;
    UserErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
