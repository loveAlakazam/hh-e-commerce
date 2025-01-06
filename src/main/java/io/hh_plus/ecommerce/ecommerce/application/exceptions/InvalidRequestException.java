package io.hh_plus.ecommerce.ecommerce.application.exceptions;

import lombok.Getter;

@Getter
public class InvalidRequestException extends IllegalArgumentException {
    private final int httpStatusCode;
    private final String errorCode;

    public InvalidRequestException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.httpStatusCode = errorCode.getHttpStatus().value();
    }
}
