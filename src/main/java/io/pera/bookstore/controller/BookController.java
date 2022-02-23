package io.pera.bookstore.controller;

import io.pera.bookstore.model.dto.BookDTO;
import io.pera.bookstore.model.request.UpdateStockRequest;
import io.pera.bookstore.service.command.BookCommandService;
import io.pera.bookstore.service.query.BookQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.net.URI;

import static io.pera.bookstore.util.Constants.API.BOOK_MAPPING;

@RestController
@RequestMapping(BOOK_MAPPING)
@AllArgsConstructor
@Validated
public class BookController {

    private final BookCommandService bookCommandService;
    private final BookQueryService bookQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@Valid @NotNull @Positive @PathVariable Long id) {
        BookDTO book = bookQueryService.getById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<URI> create(@Valid @NotNull @RequestBody BookDTO request) {
        long id = bookCommandService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO> updateStock(@Valid @NotNull @Positive @PathVariable Long id,
                                               @Valid @NotNull @RequestBody UpdateStockRequest request) {
        BookDTO book = bookCommandService.updateStock(id, request);
        return ResponseEntity.ok(book);
    }

}
