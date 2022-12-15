package com.etiya.ecommercedemopair7.business.request.baskets;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBasketRequest {
    private int id;
    private int customerId;
}
