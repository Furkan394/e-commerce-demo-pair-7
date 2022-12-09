package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.sellerProducts.AddSellerProductRequest;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.AddSellerProductResponse;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetAllSellerProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;

import java.util.List;

public interface ISellerProductService {
    DataResult<List<GetAllSellerProductResponse>> getAll();
    DataResult<AddSellerProductResponse> add(AddSellerProductRequest addSellerProductRequest);
}
