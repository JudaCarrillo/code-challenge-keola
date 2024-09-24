package com.codechallenge.keola.api.infrastructure.controllers.order;

import com.codechallenge.keola.api.application.OrderService;
import com.codechallenge.keola.api.application.dto.OrderDTO;
import com.codechallenge.keola.api.domain.order.Order;
import com.codechallenge.keola.api.infrastructure.mapper.OrderMapper;
import com.codechallenge.keola.api.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<OrderDTO> orders = orderService.getAllOrders().stream().map(OrderMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Orders found" , orders));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> getById(@PathVariable UUID orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.ok().body(ApiResponse.createErrorResponse("Order not found", null));
        }

        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Order found" , OrderMapper.toDTO(order.get())));
    }


    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody OrderDTO orderDTO) {
        Order order = OrderMapper.toEntity(orderDTO);
        orderService.newOrder(order);
        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Order created", OrderMapper.toDTO(order)));
    }


}
