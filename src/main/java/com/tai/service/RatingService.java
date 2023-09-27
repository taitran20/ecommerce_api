package com.tai.service;

import com.tai.exception.ProductException;
import com.tai.model.Rating;
import com.tai.model.User;
import com.tai.request.RatingRequest;

import java.util.List;

public interface RatingService {
    Rating createRating(RatingRequest req, User user) throws ProductException;
    List<Rating> getProductsRating(Integer productId);

}
