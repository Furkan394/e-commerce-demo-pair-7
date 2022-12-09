package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.baskets.AddBasketRequest;
import com.etiya.ecommercedemopair7.business.response.baskets.AddBasketResponse;
import com.etiya.ecommercedemopair7.business.response.baskets.GetAllBasketResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Basket;

import java.util.List;

public interface IBasketService {
    List<GetAllBasketResponse> getAll();
    Basket getById(int basketId);
    AddBasketResponse add (AddBasketRequest addBasketRequest);
}
