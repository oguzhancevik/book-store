package io.pera.bookstore.service.query;

import io.pera.bookstore.model.dto.OrderDTO;
import io.pera.bookstore.model.dto.OrderItemDTO;
import io.pera.bookstore.model.entity.Order;
import io.pera.bookstore.model.exception.BusinessValidationException;
import io.pera.bookstore.model.exception.BusinessValidationRule;
import io.pera.bookstore.model.request.OrderListRequest;
import io.pera.bookstore.repository.OrderItemRepository;
import io.pera.bookstore.repository.OrderRepository;
import io.pera.bookstore.util.BaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderQueryService {

    private final BaseMapper mapper;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderDTO getById(Long id) {
        OrderDTO order = orderRepository.findByOrderId(id)
                .orElseThrow(() -> new BusinessValidationException(BusinessValidationRule.ORDER_NOT_FOUND));
        List<OrderItemDTO> items = orderItemRepository.findByOrderId(order.getId());
        order.setItems(items);
        return order;
    }

    public List<OrderDTO> listByDateInterval(OrderListRequest request) {
        List<Order> persistedOrders = orderRepository.findByCreatedDateBetween(request.getStartDate(), request.getEndDate());
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
