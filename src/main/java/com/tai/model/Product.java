package com.tai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private double price;
    @Column(name = "discounted_price")
    private double discountPrice;
    @Column(name = "discounted_persent")
    private double discountPersent;
    private int quantity;
    private String brand;
    private String color;
    @ElementCollection
    @Column(name = "sizes")
    private Set<Size> sizeSet;
    private String imgUrl;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratingList;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList;
    @Column(name = "num_ratings")
    private int numRatings;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private LocalDateTime createdAt;
}
