package io.pera.bookstore.model.exception;

import io.pera.bookstore.util.Constants.EXCEPTION;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessValidationRule {

    CUSTOMER_NOT_FOUND("ERR-001", "Customer not found!");

    private String code = EXCEPTION.DEFAULT_CODE;
    private final String message;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST; // use only 4XX and 5XX codes

    BusinessValidationRule(String code, String message) {
        this.code = code;
        this.message = message;
    }

    BusinessValidationRule(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
