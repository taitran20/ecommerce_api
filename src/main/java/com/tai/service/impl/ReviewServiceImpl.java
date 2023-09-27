package com.tai.service.impl;

import com.tai.exception.ProductException;
import com.tai.model.Product;
import com.tai.model.Rating;
import com.tai.model.Review;
import com.tai.model.User;
import com.tai.repository.ProductRepository;
import com.tai.repository.ReviewRepository;
import com.tai.request.ReviewRequest;
import com.tai.service.ProductService;
import com.tai.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private ProductService productService;
    private ProductRepository productRepository;
    @Override
    public Review craeteReview(ReviewRequest reviewRequest, User user) throws ProductException {
        Product product = productService.findProductById(reviewRequest.getProductId());
        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());
        review.setReview(review.getReview());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Integer productId) {
        return reviewRepository.getAllReviewByProductId(productId);
    }
}
