package com.etiya.ecommercedemopair7.business.abstracts;


import com.etiya.ecommercedemopair7.business.request.productCharValues.AddProductCharValueRequest;
import com.etiya.ecommercedemopair7.business.response.productCharValues.AddProductCharValueResponse;
import com.etiya.ecommercedemopair7.business.response.productCharValues.GetAllProductCharValueResponse;

import java.util.List;

public interface IProductCharValueService {
    List<GetAllProductCharValueResponse> getAll();
    AddProductCharValueResponse add(AddProductCharValueRequest addProductCharValueRequest);
}
