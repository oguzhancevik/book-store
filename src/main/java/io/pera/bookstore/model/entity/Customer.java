package io.pera.bookstore.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Customer {

    @Id
    long id;

    Date createdDate = new Date();

    @Indexed(unique = true)
    String email;

    String firstName;

    String lastName;

}
