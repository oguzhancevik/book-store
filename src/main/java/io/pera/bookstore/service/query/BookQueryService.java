package io.pera.bookstore.service.query;

import io.pera.bookstore.model.dto.BookDTO;
import io.pera.bookstore.model.exception.BusinessValidationException;
import io.pera.bookstore.model.exception.BusinessValidationRule;
import io.pera.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookQueryService {

    private final BookRepository bookRepository;

    public BookDTO getById(Long id) {
        return bookRepository.findByBookId(id)
                .orElseThrow(() -> new BusinessValidationException(BusinessValidationRule.BOOK_NOT_FOUND));
    }

}
