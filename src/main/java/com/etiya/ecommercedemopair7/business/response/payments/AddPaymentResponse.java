package com.etiya.ecommercedemopair7.business.response.payments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPaymentResponse {
    private int id;
    private int deliveryOptionId;
    private int orderAddressId;
    private int invoiceAddressId;
    private int paymentTypeId;
    private int customerId;
    private double totalPrice;
    private boolean isVerified;
}
