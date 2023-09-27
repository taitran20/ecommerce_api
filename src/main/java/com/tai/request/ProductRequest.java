package com.tai.request;

import com.tai.model.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductRequest {
    private String title;
    private String description;
    private double price;
    private int quantity;
    private double discountPrice;
    private double discountPresent;
    private String brand;
    private String color;
    private Set<Size> sizeSet;
    private String imgUrl;
    private String topLevelCate;
    private String secondLevelCate;
    private String thirdLevelCate;

}
