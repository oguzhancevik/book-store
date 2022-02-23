package io.pera.bookstore.controller;

import io.pera.bookstore.model.dto.CustomerDTO;
import io.pera.bookstore.model.dto.OrderDTO;
import io.pera.bookstore.service.command.CustomerCommandService;
import io.pera.bookstore.service.query.CustomerQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.net.URI;
import java.util.List;

import static io.pera.bookstore.util.Constants.API.CUSTOMER_MAPPING;

@RestController
@RequestMapping(CUSTOMER_MAPPING)
@AllArgsConstructor
@Validated
public class CustomerController {

    private final CustomerCommandService customerCommandService;
    private final CustomerQueryService customerQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@Valid @NotNull @Positive @PathVariable Long id) {
        CustomerDTO customer = customerQueryService.getById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<URI> create(@Valid @NotNull @RequestBody CustomerDTO request) {
        long id = customerCommandService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@Valid @NotNull @Positive @PathVariable Long customerId,
                                                                @Valid @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                @Valid @Positive @RequestParam(required = false, defaultValue = "10") Integer size) {
        List<OrderDTO> orders = customerQueryService.getOrdersByCustomerId(customerId, page, size);
        return ResponseEntity.ok(orders);
    }

}
