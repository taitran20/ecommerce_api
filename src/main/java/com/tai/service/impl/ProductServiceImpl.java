package com.tai.service.impl;

import com.tai.model.Category;
import com.tai.model.Product;
import com.tai.repository.CategoryRepository;
import com.tai.repository.ProductRepository;
import com.tai.request.ProductRequest;
import com.tai.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    //private UserService userService;

    @Override
    public List<ProductRequest> findAll() {
        return null;
    }

    @Override
    public ProductRequest findById(Integer id) {

        return null;
    }

    @Override
    public Product findProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void createdProduct(ProductRequest productRequest) {
    }

    @Override
    public boolean updateProduct(Integer productId, Product req) {
        Optional<Product> product = productRepository.findById(productId);
        if(req.getQuantity() != 0 && product.isPresent()){
            try {
                product.get().setQuantity(req.getQuantity());
                productRepository.save(product.get());
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean insert(ProductRequest request) {
        Category topLevel = categoryRepository.findByName(request.getTopLevelCate());
        if(topLevel == null){
            Category topLevelCategory = new Category();
            topLevelCategory.setName(request.getTopLevelCate());
            topLevelCategory.setLevel(1);
            topLevel = categoryRepository.save(topLevelCategory);
        }

        //assert topLevel != null;
        Category secondLevel = categoryRepository.findByNameAndParant(request.getSecondLevelCate(), topLevel.getName());
        if(secondLevel == null){
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(request.getSecondLevelCate());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);
            secondLevel = categoryRepository.save(secondLevelCategory);
        }
        //assert secondLevel != null;
        Category thirdLevel = categoryRepository.findByNameAndParant(request.getThirdLevelCate(), secondLevel.getName());
        if(thirdLevel == null){
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(request.getThirdLevelCate());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);
            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }
        try {
            Product product = new Product();
            product.setTitle(request.getTitle());
            product.setColor(request.getColor());
            product.setBrand(request.getBrand());
            product.setCategory(thirdLevel);
            product.setDescription(request.getDescription());
            product.setCreatedAt(LocalDateTime.now());
            product.setPrice(request.getPrice());
            product.setQuantity(request.getQuantity());
            product.setDiscountPersent(request.getDiscountPresent());
            product.setDiscountPrice(request.getDiscountPrice());
            product.setImgUrl(request.getImgUrl());
            product.setSizeSet(request.getSizeSet());
            System.out.println(request.getSizeSet());

            productRepository.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean update(Integer id, ProductRequest request) {
        Optional<Product> product = productRepository.findById(id);
        if(request.getQuantity() != 0 && product.isPresent()){
            try {
                product.get().setQuantity(request.getQuantity());
                productRepository.save(product.get());
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            try {
                product.get().getSizeSet().clear();
                productRepository.delete(product.get());
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Product> findProductByCate(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Double minPrice, Double maxPrice, Double minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
        //if(category == null)
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
        System.out.println(products.size());
        if(colors!=null){
            products = products.stream().filter(p -> colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }
        if (stock != null){
            if (stock.equals("in_stock")){
                products = products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }else if (stock.equals("out_of_stock")){
                products = products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }

        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
        List<Product> pageContent = products.subList(startIndex, endIndex);
        for (Product p:
             pageContent) {
            System.out.println(p.getSizeSet());
        }
        return new PageImpl<>(pageContent,pageable, products.size());
    }
}
