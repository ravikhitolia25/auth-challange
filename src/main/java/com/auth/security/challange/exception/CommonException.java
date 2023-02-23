package com.auth.security.challange.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private Integer errorCode;
    private HttpStatus httpStatus = HttpStatus.OK;
    private Exception ex;
    private String message;

    public CommonException(Integer errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;

    }
}
