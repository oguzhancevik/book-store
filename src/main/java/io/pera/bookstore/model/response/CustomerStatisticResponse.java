package io.pera.bookstore.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerStatisticResponse {

    long totalOrderCount;
    long totalBookCount;
    BigDecimal totalPurchasedAmount;

}
