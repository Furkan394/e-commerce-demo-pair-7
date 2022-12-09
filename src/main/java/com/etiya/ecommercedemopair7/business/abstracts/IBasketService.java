package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.baskets.AddBasketRequest;
import com.etiya.ecommercedemopair7.business.response.baskets.AddBasketResponse;
import com.etiya.ecommercedemopair7.business.response.baskets.GetAllBasketResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Basket;

import java.util.List;

public interface IBasketService {
    DataResult<List<GetAllBasketResponse>> getAll();
    DataResult<Basket> getById(int basketId);
    DataResult<AddBasketResponse> add (AddBasketRequest addBasketRequest);
}
