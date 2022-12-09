package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.productChars.AddProductCharRequest;

import com.etiya.ecommercedemopair7.business.response.productChars.AddProductCharResponse;
import com.etiya.ecommercedemopair7.business.response.productChars.GetAllProductCharResponse;
import com.etiya.ecommercedemopair7.business.response.productChars.GetProductCharResponse;
import com.etiya.ecommercedemopair7.entities.concretes.ProductChar;

import java.util.List;


public interface IProductCharService {
    List<GetAllProductCharResponse> getAll();
    GetProductCharResponse getById(int productCharId);
    ProductChar getByProductCharId(int productCharId);
    AddProductCharResponse add(AddProductCharRequest addProductCharRequest);
}
