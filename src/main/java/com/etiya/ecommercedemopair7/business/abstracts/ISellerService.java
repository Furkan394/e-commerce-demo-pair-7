package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.sellers.AddSellerRequest;
import com.etiya.ecommercedemopair7.business.response.sellers.AddSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetAllSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetSellerResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Seller;

import java.util.List;

public interface ISellerService {
    List<GetAllSellerResponse> getAll();
    GetSellerResponse getById(int sellerId);
    Seller getBySellerId(int sellerId);
    AddSellerResponse add(AddSellerRequest addSellerRequest);
}
