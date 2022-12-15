package com.etiya.ecommercedemopair7.business.request.orderItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderItemRequest {
    private int quantity;
    private double itemTotalPrice;
    private int orderId;
    private int sellerProductId;
    private int customerId;
}
