package io.pera.bookstore.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderListRequest {

    @NotNull Date startDate;
    @NotNull Date endDate;

}
