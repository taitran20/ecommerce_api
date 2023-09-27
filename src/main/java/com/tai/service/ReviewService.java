package com.tai.service;

import com.tai.exception.ProductException;
import com.tai.model.Rating;
import com.tai.model.Review;
import com.tai.model.User;
import com.tai.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    Review craeteReview(ReviewRequest reviewRequest, User user) throws ProductException;
    List<Review> getAllReview(Integer productId);
}
