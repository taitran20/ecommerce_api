package com.tai.service;

import com.tai.model.Product;
import com.tai.request.ProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService extends BaseService<ProductRequest, Integer>{
    Product findProductById(Integer id);

    List<Product> getAll();
    void createdProduct(ProductRequest productRequest);
    boolean updateProduct(Integer productId, Product req);
    List<Product> findProductByCate(String category);
    Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Double minPrice, Double maxPrice,
                                Double minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
}
