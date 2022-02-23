package io.pera.bookstore.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    long id;

    Date createdDate = new Date();

    String name;

    String author;

    long stockAmount;

    BigDecimal price;

    @Version
    long version;
}
