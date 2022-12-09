package com.etiya.ecommercedemopair7.business.response.productCharValues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductCharValueResponse {
    private int id;
    private String name;
    private int productCharId;
}
