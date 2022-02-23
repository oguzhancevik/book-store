package io.pera.bookstore.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {

    long id;

    Date createdDate = new Date();

    long customerId;

    BigDecimal price;

    List<OrderItemDTO> items;

}
