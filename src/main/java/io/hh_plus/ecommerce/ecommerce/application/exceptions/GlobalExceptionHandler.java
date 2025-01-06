package io.hh_plus.ecommerce.ecommerce.application.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    // InvalidRequestException 에러 처리
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
        ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getHttpStatusCode()));
    }

    // BusinessException 에러 처리
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRequestException(BusinessException ex) {
        ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getHttpStatusCode()));
    }

    // 그외 알 수 없는 에러 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownExceptions(Exception ex) {
        String detailMessage = ex.getMessage() != null ? ex.getMessage() : "에러 상세 정보가 없습니다.";

        // todo: 하드코딩된 errorCode, message Map으로 분리 필요
        //  key: UNKNOWN_ERROR / value:  { message: "예기치 못한 에러가 발생하였습니다.", httpStatusCode: 500 }
        ErrorResponse response = new ErrorResponse("UNKNOWN_ERROR", "예기치 못한 에러가 발생하였습니다.", detailMessage);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Getter
    private static class ErrorResponse {
        private final String errorCode;
        private final String message;
        private final String detail; // (선택) 상세메시지


        // 생성자: errorCode, message, detail 구성
        public ErrorResponse(String errorCode, String message, String detail) {
            this.errorCode = errorCode;
            this.message = message;
            this.detail = detail;
        }
        // 생성자: errorCode, message 만 구성
        public ErrorResponse(String errorCode, String message) {
            this(errorCode, message, null); // detail 을 null 로 설정
        }
    }
}
