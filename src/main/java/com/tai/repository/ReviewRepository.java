package com.tai.repository;

import com.tai.model.Rating;
import com.tai.model.Review;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("select r from Review r where r.product.id =: product_id")
    List<Review> getAllReviewByProductId(@Param("product_id") Integer id);
}
