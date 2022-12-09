package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.sellerProducts.AddSellerProductRequest;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.AddSellerProductResponse;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetAllSellerProductResponse;

import java.util.List;

public interface ISellerProductService {
    List<GetAllSellerProductResponse> getAll();
    AddSellerProductResponse add(AddSellerProductRequest addSellerProductRequest);
}
