package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.baskets.AddBasketRequest;
import com.etiya.ecommercedemopair7.business.response.baskets.AddBasketResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Basket;

public interface IBasketService {
    Basket getById(int basketId);
    AddBasketResponse add (AddBasketRequest addBasketRequest);
}
