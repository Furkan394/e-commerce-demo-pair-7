package com.etiya.ecommercedemopair7.business.request.payments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPaymentRequest {
    private int deliveryOptionId;
    private int orderAddressId;
    private int invoiceAddressId;
    private int paymentTypeId;
    private int customerId;
    private boolean isVerified;
}
