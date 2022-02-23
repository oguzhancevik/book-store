package io.pera.bookstore.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Positive;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemDTO {

    long orderId;

    BookDTO book;

    @Positive
    int quantity;

}
