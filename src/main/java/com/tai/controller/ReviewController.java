package com.tai.controller;

import com.tai.exception.ProductException;
import com.tai.exception.UserException;
import com.tai.model.Rating;
import com.tai.model.Review;
import com.tai.model.User;
import com.tai.repository.ReviewRepository;
import com.tai.request.ReviewRequest;
import com.tai.service.ReviewService;
import com.tai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req, @RequestHeader("Authorization")String jwt) throws UserException, ProductException {
        User user = userService.getUserProfileByJwt(jwt);
        Review review = reviewService.craeteReview(req, user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductRatings(@RequestHeader("Authorization")String jwt, @PathVariable Integer productId) throws UserException {
        User user = userService.getUserProfileByJwt(jwt);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Review> reviews = reviewService.getAllReview(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
