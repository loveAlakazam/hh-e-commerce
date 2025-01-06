package io.hh_plus.ecommerce.ecommerce.application.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String errorCode;
    private final int httpStatusCode;


    public BusinessException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.httpStatusCode = errorCode.getHttpStatus().value();
    }

}
