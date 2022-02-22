package io.pera.bookstore.model.response;

import io.pera.bookstore.util.Constants.EXCEPTION;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HttpErrorDTO {

    @Builder.Default
    private String code = EXCEPTION.DEFAULT_CODE;

    private String message;

    @Builder.Default
    private Date date = new Date();

}
