package com.etiya.ecommercedemopair7.business.request.categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryRequest {
    private int refId;
    private String name;
}
