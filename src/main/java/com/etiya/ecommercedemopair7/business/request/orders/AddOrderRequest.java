package com.etiya.ecommercedemopair7.business.request.orders;

import com.etiya.ecommercedemopair7.entities.concretes.Address;
import com.etiya.ecommercedemopair7.entities.concretes.DeliveryOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRequest {
    @Min(1)
    private double totalPrice;
    private LocalDate orderDate;
    @Min(1)
    private int deliveryOptionId;
    @Min(1)
    private int orderAddressId;
    @Min(1)
    private int invoiceAddressId;
    @Min(1)
    private int paymentId;
}
