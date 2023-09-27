package com.tai.controller;

import com.tai.exception.ProductException;
import com.tai.model.Product;
import com.tai.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,
                                                                      @RequestParam List<String> color,
                                                                      @RequestParam List<String> size,
                                                                      @RequestParam Double minPrice,
                                                                      @RequestParam Double maxPrice,
                                                                      @RequestParam Double minDiscount,
                                                                      @RequestParam String sort,
                                                                      @RequestParam String stock,
                                                                      @RequestParam Integer pageNumber,
                                                                      @RequestParam Integer pageSize){
        Page<Product> products = productService.getAllProduct(category,color,size,minPrice,maxPrice,minDiscount,sort,stock,pageNumber,pageSize);
        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Integer productId) throws ProductException{
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
