package com.tai.repository;

import com.tai.model.Product;
import com.tai.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query("select r from Rating r where r.product.id =: product_id")
    List<Rating> getAllRatingByProductId(@Param("product_id") Integer id);
}
