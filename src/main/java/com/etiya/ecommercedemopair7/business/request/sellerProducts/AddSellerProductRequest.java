package com.etiya.ecommercedemopair7.business.request.sellerProducts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSellerProductRequest {
    private int sellerId;
    private int productId;
    private String description;
    private String imageUrl;
    @Min(0)
    private int stock;
    @Min(1)
    private double unitPrice;
}
