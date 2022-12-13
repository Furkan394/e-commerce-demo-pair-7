package com.etiya.ecommercedemopair7.entities.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private int id;
    private int orderNumber;
    private double totalPrice;
    private LocalDate orderDate;
    private String deliveryOptionName;
    private AddressDto invoiceAddressDto;
    private AddressDto orderAddressDto;
    private List<OrderItemDto> orderItemDto;

}
