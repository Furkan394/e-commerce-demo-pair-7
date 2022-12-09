package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.productChars.AddProductCharRequest;

import com.etiya.ecommercedemopair7.business.response.productChars.AddProductCharResponse;
import com.etiya.ecommercedemopair7.business.response.productChars.GetAllProductCharResponse;
import com.etiya.ecommercedemopair7.business.response.productChars.GetProductCharResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.ProductChar;

import java.util.List;


public interface IProductCharService {
    DataResult<List<GetAllProductCharResponse>> getAll();
    DataResult<GetProductCharResponse> getById(int productCharId);
    DataResult<ProductChar> getByProductCharId(int productCharId);
    DataResult<AddProductCharResponse> add(AddProductCharRequest addProductCharRequest);
}
