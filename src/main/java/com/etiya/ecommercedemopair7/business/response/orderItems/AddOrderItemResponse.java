package com.etiya.ecommercedemopair7.business.response.orderItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderItemResponse {
    private int id;
    private int quantity;
    private double itemTotalPrice;
    private int orderId;
    private int sellerProductId;
    private int customerId;
}
