package com.etiya.ecommercedemopair7.entities.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private int id;
    private int quantity;
    private double itemTotalPrice;
    private String orderNumber;
    private double totalPrice;
    private LocalDate orderDate;
    private String deliveryOptionName;
    private String orderAddress;
    private String invoiceAddress;
    private String productName;
    private String description;
    private String imageUrl;
    private int stock;
    private double unitPrice;

}
