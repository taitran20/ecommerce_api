package com.tai.repository;

import com.tai.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
    @Query("select c from Category c where c.name = :name and c.parentCategory.name = :parentCategoryName")
    Category findByNameAndParant(@Param("name")String name, @Param("parentCategoryName")String parentCategoryName);

}
