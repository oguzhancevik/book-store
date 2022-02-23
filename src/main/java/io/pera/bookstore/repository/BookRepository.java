package io.pera.bookstore.repository;

import io.pera.bookstore.model.dto.BookDTO;
import io.pera.bookstore.model.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    @Query(value = "{'id' : ?0}")
    Optional<BookDTO> findByBookId(Long id);

}
