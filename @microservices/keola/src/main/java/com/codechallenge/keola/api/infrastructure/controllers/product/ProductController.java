package com.codechallenge.keola.api.infrastructure.controllers.product;

import com.codechallenge.keola.api.application.ProductService;
import com.codechallenge.keola.api.domain.product.Product;
import com.codechallenge.keola.api.utils.ApiResponse;
import com.codechallenge.keola.api.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getProducts() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Products found", products));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse> getProductByCode(@PathVariable String code) {
        Optional<Product> product = productService.getByCode(code);
        if (product.isEmpty()) {
            return ResponseEntity.badRequest().body(ApiResponse.createErrorResponse("Product not found", ErrorCodes.PRODUCT_NOT_FOUND.getCode()));
        }

        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Product found", product.get()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody Product product) {
        boolean success = productService.create(product);

        if (!success) {
            return ResponseEntity.badRequest().body(ApiResponse.createErrorResponse("Product already exists", ErrorCodes.PRODUCT_EXISTS.getCode()));
        }

        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Product created successfully", product));
    }

    @PutMapping("/{code}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable String code, @RequestBody Product product) {
        boolean success = productService.update(code, product);

        if (!success) {
            return ResponseEntity.badRequest().body(ApiResponse.createErrorResponse("Product not updated", ErrorCodes.PRODUCT_NOT_UPDATED.getCode()));
        }

        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Product updated successfully", product));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String code) {
        boolean success = productService.delete(code);

        if (!success) {
            return ResponseEntity.badRequest().body(ApiResponse.createErrorResponse("Product not found", ErrorCodes.PRODUCT_NOT_FOUND.getCode()));
        }

        return ResponseEntity.ok().body(ApiResponse.createSuccessResponse("Product deleted successfully", null));
    }

}
