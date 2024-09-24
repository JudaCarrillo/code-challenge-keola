package com.codechallenge.keola.api.domain.order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IOrderService {

    List<Order> getAllOrders();
    Optional<Order> getOrderById(UUID id);
    void newOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(UUID id);

}
