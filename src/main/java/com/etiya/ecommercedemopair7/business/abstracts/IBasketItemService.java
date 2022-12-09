package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.basketItems.AddBasketItemRequest;
import com.etiya.ecommercedemopair7.business.response.basketItems.AddBasketItemResponse;
import com.etiya.ecommercedemopair7.business.response.basketItems.GetAllBasketItemResponse;

import java.util.List;

public interface IBasketItemService {
    List<GetAllBasketItemResponse> getAll();
    AddBasketItemResponse add(AddBasketItemRequest addBasketItemRequest);
}
