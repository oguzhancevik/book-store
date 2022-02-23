package io.pera.bookstore.repository;

import io.pera.bookstore.model.dto.OrderDTO;
import io.pera.bookstore.model.entity.Order;
import io.pera.bookstore.model.response.CustomerStatisticResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

    @Query(value = "{'id' : ?0}")
    Optional<OrderDTO> findByOrderId(Long id);

    List<Order> findByCreatedDateBetween(Date startDate, Date endDate);

    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

}
