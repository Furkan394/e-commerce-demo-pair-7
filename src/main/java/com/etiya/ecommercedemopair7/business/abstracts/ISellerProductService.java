package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.sellerProducts.AddSellerProductRequest;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.AddSellerProductResponse;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetAllSellerProductResponse;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetSellerProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.SellerProduct;

import java.util.List;

public interface ISellerProductService {
    DataResult<List<GetAllSellerProductResponse>> getAll();
    DataResult<AddSellerProductResponse> add(AddSellerProductRequest addSellerProductRequest);
    DataResult<GetSellerProductResponse> getById(int id);
    SellerProduct getBySellerProductId(int id);
}
