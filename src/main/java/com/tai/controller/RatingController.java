package com.tai.controller;

import com.tai.exception.ProductException;
import com.tai.exception.UserException;
import com.tai.model.Rating;
import com.tai.model.User;
import com.tai.request.RatingRequest;
import com.tai.service.RatingService;
import com.tai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ratings")
public class RatingController {
    private RatingService ratingService;
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request, @RequestHeader("Authorization")String jwt) throws UserException, ProductException {
        User user = userService.getUserProfileByJwt(jwt);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Rating rating = ratingService.createRating(request, user);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);

    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRatings(@RequestHeader("Authorization")String jwt, @PathVariable Integer productId) throws UserException {
        User user = userService.getUserProfileByJwt(jwt);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Rating> ratings = ratingService.getProductsRating(productId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}
