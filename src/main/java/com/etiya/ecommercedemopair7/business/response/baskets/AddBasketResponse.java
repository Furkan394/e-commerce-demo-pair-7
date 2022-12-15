package com.etiya.ecommercedemopair7.business.response.baskets;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBasketResponse {
    private int id;
    private int customerId;
    private double totalPrice;
    private double shippingPrice;
}
