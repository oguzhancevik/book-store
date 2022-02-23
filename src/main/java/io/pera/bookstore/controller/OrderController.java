package io.pera.bookstore.controller;

import io.pera.bookstore.model.dto.OrderDTO;
import io.pera.bookstore.model.request.OrderCreateRequest;
import io.pera.bookstore.model.request.OrderListRequest;
import io.pera.bookstore.service.command.OrderCommandService;
import io.pera.bookstore.service.query.OrderQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

import static io.pera.bookstore.util.Constants.API.ORDER_MAPPING;

@RestController
@RequestMapping(ORDER_MAPPING)
@AllArgsConstructor
@Validated
public class OrderController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(@Valid @NotNull @Positive @PathVariable Long id) {
        OrderDTO order = orderQueryService.getById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/date-interval")
    public ResponseEntity<List<OrderDTO>> listByDateInterval(@Valid @NotNull @RequestBody OrderListRequest request) {
        List<OrderDTO> orders = orderQueryService.listByDateInterval(request);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<URI> create(@Valid @NotNull @RequestBody OrderCreateRequest request) {
        long id = orderCommandService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

}
