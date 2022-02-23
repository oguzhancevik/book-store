package io.pera.bookstore.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {

    @Id
    long id;

    private long orderId;

    Book book;

    int quantity;

}
