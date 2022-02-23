package io.pera.bookstore.service.query;

import io.pera.bookstore.model.dto.CustomerDTO;
import io.pera.bookstore.model.dto.OrderDTO;
import io.pera.bookstore.model.dto.OrderItemDTO;
import io.pera.bookstore.model.entity.Order;
import io.pera.bookstore.model.exception.BusinessValidationException;
import io.pera.bookstore.model.exception.BusinessValidationRule;
import io.pera.bookstore.repository.CustomerRepository;
import io.pera.bookstore.repository.OrderItemRepository;
import io.pera.bookstore.repository.OrderRepository;
import io.pera.bookstore.util.BaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerQueryService {

    private final BaseMapper mapper;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public CustomerDTO getById(Long id) {
        return customerRepository.findByCustomerId(id)
                .orElseThrow(() -> new BusinessValidationException(BusinessValidationRule.CUSTOMER_NOT_FOUND));
    }

    public List<OrderDTO> getOrdersByCustomerId(Long customerId, Integer page, Integer size) {
        Page<Order> pageResponse = orderRepository.findByCustomerId(customerId, PageRequest.of(page, size));
        List<Order> persistedOrders = pageResponse.get().collect(Collectors.toList());
        List<OrderDTO> orders = new ArrayList<>();
        for (Order persistedOrder : persistedOrders) {
            OrderDTO order = mapper.toOrderDTO(persistedOrder);
            List<OrderItemDTO> items = orderItemRepository.findByOrderId(order.getId());
            order.setItems(items);
            orders.add(order);
        }
        return orders;
    }

}
