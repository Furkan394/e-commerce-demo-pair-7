package com.etiya.ecommercedemopair7.business.request.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInvoiceRequest {
    private String number;
    private LocalDate createdDate;
    private int orderId;
}
