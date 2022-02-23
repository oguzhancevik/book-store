package io.pera.bookstore.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date createdDate = new Date();

    Long customerId;

    @Field(targetType = FieldType.DECIMAL128)
    BigDecimal price = BigDecimal.ZERO;

    int itemCount;

}
