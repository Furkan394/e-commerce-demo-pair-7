package com.etiya.ecommercedemopair7.business.response.sellerProducts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetSellerProductResponse {
    private int id;
    private int sellerId;
    private int productId;
    private String description;
    private String imageUrl;
    private int stock;
    private double unitPrice;
}
