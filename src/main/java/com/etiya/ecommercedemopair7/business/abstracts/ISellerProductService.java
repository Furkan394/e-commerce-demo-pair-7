package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.sellerProducts.AddSellerProductRequest;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.AddSellerProductResponse;

public interface ISellerProductService {
    AddSellerProductResponse add(AddSellerProductRequest addSellerProductRequest);
}
