package io.pera.bookstore.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDTO {

    long id;

    Date createdDate;

    @NotNull
    @Email
    String email;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

}
