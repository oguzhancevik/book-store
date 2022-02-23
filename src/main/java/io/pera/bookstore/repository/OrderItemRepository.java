package io.pera.bookstore.repository;

import io.pera.bookstore.model.dto.OrderItemDTO;
import io.pera.bookstore.model.entity.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, Long> {

    List<OrderItemDTO> findByOrderId(long id);

}
