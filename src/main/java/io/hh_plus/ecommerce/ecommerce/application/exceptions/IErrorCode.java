package io.hh_plus.ecommerce.ecommerce.application.exceptions;

import org.springframework.http.HttpStatus;

public interface IErrorCode {
    HttpStatus getHttpStatus(); // Http 상태코드 반환
    String getMessage(); // 에러메시지 반환
    String getCode(); // 에러코드 반환
}
