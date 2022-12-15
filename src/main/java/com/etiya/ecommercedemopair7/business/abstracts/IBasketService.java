package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.baskets.AddBasketRequest;
import com.etiya.ecommercedemopair7.business.request.baskets.UpdateBasketRequest;
import com.etiya.ecommercedemopair7.business.response.baskets.AddBasketResponse;
import com.etiya.ecommercedemopair7.business.response.baskets.GetAllBasketResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBasketService {
    DataResult<List<GetAllBasketResponse>> getAll();
    Basket createBasket (AddBasketRequest addBasketRequest);
    DataResult<AddBasketResponse> updateBasket (UpdateBasketRequest updateBasketRequest, Basket getBasket);
    Basket getByCustomerId(int customerId);
    DataResult<Page<GetAllBasketResponse>> getAllBasketsWithPage(Pageable pageable);
}
