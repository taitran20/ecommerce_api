package com.tai.service.impl;

import com.tai.exception.ProductException;
import com.tai.model.Product;
import com.tai.model.Rating;
import com.tai.model.User;
import com.tai.repository.RatingRepository;
import com.tai.request.RatingRequest;
import com.tai.service.ProductService;
import com.tai.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;
    private ProductService productService;
    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setCreatedAt(LocalDateTime.now());
        rating.setUser(user);
        rating.setRating(req.getRating());
        //rating.set
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Integer productId) {
        return ratingRepository.getAllRatingByProductId(productId);
    }
}
