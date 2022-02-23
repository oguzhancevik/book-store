package io.pera.bookstore.util;

import io.pera.bookstore.model.dto.BookDTO;
import io.pera.bookstore.model.dto.CustomerDTO;
import io.pera.bookstore.model.dto.OrderDTO;
import io.pera.bookstore.model.entity.Book;
import io.pera.bookstore.model.entity.Customer;
import io.pera.bookstore.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static io.pera.bookstore.util.Constants.MAPPER;

@Mapper(componentModel = MAPPER.COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Customer fromCustomerDTO(CustomerDTO request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Book fromBookDTO(BookDTO request);

    BookDTO toBookDTO(Book request);

    OrderDTO toOrderDTO(Order request);

}
