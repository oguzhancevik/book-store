package io.pera.bookstore.repository;

import io.pera.bookstore.model.dto.CustomerDTO;
import io.pera.bookstore.model.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Query(value = "{'id' : ?0}")
    Optional<CustomerDTO> findByCustomerId(Long id);

    Optional<Customer> findByEmail(String email);

}
