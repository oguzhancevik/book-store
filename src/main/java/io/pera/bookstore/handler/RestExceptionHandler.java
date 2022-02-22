package io.pera.bookstore.handler;

import io.pera.bookstore.model.exception.BusinessValidationException;
import io.pera.bookstore.model.response.HttpErrorDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        log.error("", ex);

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        HttpErrorDTO httpError = HttpErrorDTO.builder()
                .message(ex.getMessage())
                .build();

        if (ex instanceof BusinessValidationException) {
            BusinessValidationException businessException = (BusinessValidationException) ex;
            httpError.setCode(businessException.getValidationRule().getCode());
            httpStatus = businessException.getValidationRule().getHttpStatus();
        }

        return handleExceptionInternal(ex, httpError, new HttpHeaders(), httpStatus, request);
    }

}