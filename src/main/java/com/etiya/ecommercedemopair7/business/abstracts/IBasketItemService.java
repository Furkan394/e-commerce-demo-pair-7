package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.basketItems.AddBasketItemRequest;
import com.etiya.ecommercedemopair7.business.response.basketItems.AddBasketItemResponse;
import com.etiya.ecommercedemopair7.business.response.basketItems.GetAllBasketItemResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.BasketItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface IBasketItemService {
    DataResult<List<GetAllBasketItemResponse>> getAll();
    DataResult<AddBasketItemResponse> add(AddBasketItemRequest addBasketItemRequest);
    List<BasketItem> getByBasketItemId(int basketItemId);
    DataResult<Slice<GetAllBasketItemResponse>> getAllBasketItemsWithSlice(Pageable pageable);
}
