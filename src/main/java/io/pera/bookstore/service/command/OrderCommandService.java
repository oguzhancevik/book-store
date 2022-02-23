package io.pera.bookstore.service.command;

import io.pera.bookstore.model.dto.OrderItemDTO;
import io.pera.bookstore.model.entity.Book;
import io.pera.bookstore.model.entity.Customer;
import io.pera.bookstore.model.entity.Order;
import io.pera.bookstore.model.entity.OrderItem;
import io.pera.bookstore.model.exception.BusinessValidationException;
import io.pera.bookstore.model.exception.BusinessValidationRule;
import io.pera.bookstore.model.request.OrderCreateRequest;
import io.pera.bookstore.repository.BookRepository;
import io.pera.bookstore.repository.CustomerRepository;
import io.pera.bookstore.repository.OrderItemRepository;
import io.pera.bookstore.repository.OrderRepository;
import io.pera.bookstore.util.Constants.SEQUENCE;
import io.pera.bookstore.util.SequenceUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderCommandService {

    private final SequenceUtil sequenceUtil;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public long create(@Valid @NotNull OrderCreateRequest request) {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessValidationException(BusinessValidationRule.CUSTOMER_NOT_FOUND));

        Order order = new Order();
        order.setCustomerId(customer.getId());

        order.setId(sequenceUtil.generateSequence(SEQUENCE.ORDER));

        List<Book> books = new ArrayList<>();

        for (OrderItemDTO requestItem : request.getItems()) {
            Book book = bookRepository.findById(requestItem.getBook().getId())
                    .orElseThrow(() -> new BusinessValidationException(BusinessValidationRule.BOOK_NOT_FOUND));
            books.add(book);

            if (book.getStockAmount() < requestItem.getQuantity())
                throw new BusinessValidationException(BusinessValidationRule.INSUFFICIENT_STOCK);

            OrderItem item = new OrderItem();
            item.setId(sequenceUtil.generateSequence(SEQUENCE.ORDER_ITEM));
            item.setBook(book);
            item.setQuantity(requestItem.getQuantity());
            item.setOrderId(order.getId());
            orderItemRepository.save(item);
            decreaseStock(requestItem, book);
            order.setPrice(order.getPrice().add(item.getBook().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))));
            order.setItemCount(order.getItemCount() + 1);
        }

        order = orderRepository.save(order);

        return order.getId();
    }

    private void decreaseStock(OrderItemDTO requestItem, Book book) {
        book.setStockAmount(book.getStockAmount() - requestItem.getQuantity());
        bookRepository.save(book);
    }

}
