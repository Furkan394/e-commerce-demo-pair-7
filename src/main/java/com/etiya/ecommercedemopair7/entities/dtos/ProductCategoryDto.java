package com.etiya.ecommercedemopair7.entities.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {
    private int id;
    private String productName;
    private String categoryName;
    private int refId;
}