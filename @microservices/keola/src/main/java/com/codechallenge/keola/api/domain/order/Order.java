package com.codechallenge.keola.api.domain.order;


import com.codechallenge.keola.api.domain.product.Product;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "orders")
public class Order {

    @Id
    @GeneratedValue
    private UUID orderId;
    private String customerId;
    private List<Product> products;
    private BigDecimal total;
    private OrderStatus status;
    private Timestamp updatedAt;
    private Timestamp createdAt;


    public enum OrderStatus {
        CREATED,
        PAID,
        CANCELLED
    }

}
