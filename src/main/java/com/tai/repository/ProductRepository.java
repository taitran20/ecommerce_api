package com.tai.repository;

import com.tai.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p " +
            "where (p.category.name = :category or :category ='' or p.category.parentCategory.name = :category or p.category.parentCategory.parentCategory.name = :category)"+
            "and ((:minPrice is null and :maxPrice is null) or (p.discountPrice between :minPrice and :maxPrice)) "+
    "and (:minDiscount is null or p.discountPersent >= :minDiscount) "+
    "order by "+
    "case when :sort = 'price_low' then p.discountPrice end ASC,"+
    "case when :sort = 'price_high' then p.discountPrice end DESC")
    List<Product> filterProducts(@Param("category") String category,
                                        @Param("minPrice") Double minPrice,
                                        @Param("maxPrice") Double maxPrice,
                                        @Param("minDiscount") Double minDiscount,
                                        @Param("sort") String sort);
}
