package com.etiya.ecommercedemopair7.business.response.baskets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBasketResponse {
    private int id;
    private double totalPrice;
    private double shippingPrice;
    private int customerId;
}
