package io.pera.bookstore.model.request;

import io.pera.bookstore.model.dto.OrderItemDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreateRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private List<@Valid OrderItemDTO> items;

}
