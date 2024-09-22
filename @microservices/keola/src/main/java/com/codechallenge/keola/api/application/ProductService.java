package com.codechallenge.keola.api.application;

import com.codechallenge.keola.api.domain.product.IProductRepository;
import com.codechallenge.keola.api.domain.product.IProductService;
import com.codechallenge.keola.api.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getByCode(String code) {
        return productRepository.findProductByCode(code);
    }

    @Override
    public boolean create(Product product) {
        Optional<Product> productExist = productRepository.findProductByCode(product.getCode());

        if (productExist.isPresent()) {
            return false;
        }

        productRepository.save(product);
        return true;
    }

    @Override
    public boolean update(String code, Product product) {
        Optional<Product> existingProductOpt = productRepository.findProductByCode(code);
        if (existingProductOpt.isEmpty()) {
            return false;
        }

        Product existingProduct = existingProductOpt.get();

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());

        productRepository.save(product);
        return true;
    }

    @Override
    public boolean delete(String code) {
        Optional<Product> product = productRepository.findProductByCode(code);
        if (product.isEmpty()) {
            return false;
        }
        productRepository.delete(product.get());
        return true;
    }
}
