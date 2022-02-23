package io.pera.bookstore.service.command;

import io.pera.bookstore.model.dto.BookDTO;
import io.pera.bookstore.model.entity.Book;
import io.pera.bookstore.model.exception.BusinessValidationException;
import io.pera.bookstore.model.exception.BusinessValidationRule;
import io.pera.bookstore.model.request.UpdateStockRequest;
import io.pera.bookstore.repository.BookRepository;
import io.pera.bookstore.util.BaseMapper;
import io.pera.bookstore.util.Constants.SEQUENCE;
import io.pera.bookstore.util.SequenceUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookCommandService {

    private final BaseMapper mapper;
    private final SequenceUtil sequenceUtil;
    private final BookRepository bookRepository;

    public long create(BookDTO request) {
        Book book = mapper.fromBookDTO(request);
        book.setId(sequenceUtil.generateSequence(SEQUENCE.BOOK));
        book = bookRepository.save(book);
        return book.getId();
    }

    public BookDTO updateStock(Long id, UpdateStockRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessValidationException(BusinessValidationRule.BOOK_NOT_FOUND));
        book.setStockAmount(request.getStockAmount());
        bookRepository.save(book);
        return mapper.toBookDTO(book);
    }

}
