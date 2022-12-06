package com.etiya.ecommercedemopair7.business.request.productCategories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductCategoryRequest {
    private int productId;
    private int categoryId;
}
