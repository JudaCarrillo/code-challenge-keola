package com.codechallenge.keola.api.application.dto;

import com.codechallenge.keola.api.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDTO {

    private UUID orderId;
    private String customerId;
    private List<Product> products;
    private BigDecimal total;
    private Timestamp updatedAt;
    private Timestamp createdAt;

}
