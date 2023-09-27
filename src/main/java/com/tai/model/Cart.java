package com.tai.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
   // @JoinColumn(name = "cart_item")
    private Set<CartItem> cartItems;
    @Column(name = "total_price")
    private double totalPrice;
    private int totalItem;
    private double totalDiscountPrice;
    private double discounte;
}
