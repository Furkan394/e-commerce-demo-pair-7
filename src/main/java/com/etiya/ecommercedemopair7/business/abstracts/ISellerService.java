package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.sellers.AddSellerRequest;
import com.etiya.ecommercedemopair7.business.response.productCharValues.GetAllProductCharValueResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.AddSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetAllSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetSellerResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISellerService {
    DataResult<List<GetAllSellerResponse>> getAll();
    DataResult<GetSellerResponse> getById(int sellerId);
    DataResult<Seller> getBySellerId(int sellerId);
    DataResult<AddSellerResponse> add(AddSellerRequest addSellerRequest);
    DataResult<Page<GetAllSellerResponse>> getSellersWithPage(Pageable pageable);

}
