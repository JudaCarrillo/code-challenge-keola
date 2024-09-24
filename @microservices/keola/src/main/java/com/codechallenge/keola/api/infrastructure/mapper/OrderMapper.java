package com.codechallenge.keola.api.infrastructure.mapper;

import com.codechallenge.keola.api.application.dto.OrderDTO;
import com.codechallenge.keola.api.domain.order.Order;

public class OrderMapper {

    public static Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setCustomerId(orderDTO.getCustomerId());

        return order;
    }

    public static OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCustomerId(order.getCustomerId());

        return orderDTO;
    }

}
