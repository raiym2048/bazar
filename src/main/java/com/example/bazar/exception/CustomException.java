package com.example.bazar.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "httpStatus=" + httpStatus +
                ", message=" + getMessage() +
                '}';
    }
}
