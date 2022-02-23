package io.pera.bookstore.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {

    long id;

    Date createdDate;

    @NotNull
    String name;

    @NotNull
    String author;

    @NotNull
    @PositiveOrZero
    long stockAmount;

    @NotNull
    @Positive
    BigDecimal price;

}
