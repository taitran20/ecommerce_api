package com.tai.controller;

import com.tai.exception.ProductException;
import com.tai.model.Product;
import com.tai.request.ProductRequest;
import com.tai.response.ApiResponse;
import com.tai.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@AllArgsConstructor
public class AdminProductController {
    private ProductService productService;

    @PostMapping("/create")
    public Object createProduct(@RequestBody ProductRequest request){
        //System.out.println(request.getTitle());
        System.out.println(request.getSizeSet());
        boolean result = productService.insert(request);
        //System.out.println(result);
        if (!result){
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Create product Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/{productId}/delete")
    public Object deleteProduct(@PathVariable("productId") Integer productId) throws ProductException{
        productService.delete(productId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Product delete Successfully");
        apiResponse.setStatus(true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> productList = productService.getAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PutMapping("/{productId}/update")
    public Object updateProduct(@RequestBody Product req, @PathVariable("productId") Integer productId) throws ProductException{
        boolean result = productService.updateProduct(productId, req);
        if (!result){
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Update product Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public Object createMultipleProduct(@RequestBody ProductRequest[] requests){
        for (ProductRequest productRequest: requests){
            productService.createdProduct(productRequest);
        }
        ApiResponse apiResponse = new ApiResponse("Product created successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
