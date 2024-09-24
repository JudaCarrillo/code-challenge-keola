package com.codechallenge.keola.api.application;

import com.codechallenge.keola.api.domain.order.IOrderRepository;
import com.codechallenge.keola.api.domain.order.IOrderService;
import com.codechallenge.keola.api.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public void newOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        Order.OrderStatus status = order.getStatus();

        switch (status) {
            case CREATED:
                order.setStatus(Order.OrderStatus.PAID);
                break;
            case PAID:
                order.setStatus(Order.OrderStatus.CANCELLED);
                break;
            case CANCELLED:
                order.setStatus(Order.OrderStatus.CREATED);
                break;
        }

        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }



}
