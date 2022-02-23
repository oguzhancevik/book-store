package io.pera.bookstore.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class UpdateStockRequest {

    @NotNull
    @PositiveOrZero
    long stockAmount;

}
