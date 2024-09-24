package com.codechallenge.keola.api.domain.order;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IOrderRepository extends MongoRepository<Order, UUID> {
}
