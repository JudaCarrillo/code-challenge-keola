package com.codechallenge.keola.api.domain.product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Optional<Product> getByCode(String code);
    List<Product> getAll();
    boolean create(Product product);
    boolean update(String code, Product product);
    boolean delete(String code);

}
