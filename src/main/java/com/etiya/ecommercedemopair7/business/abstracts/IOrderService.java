package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.orders.AddOrderRequest;
import com.etiya.ecommercedemopair7.business.response.orders.AddOrderResponse;
import com.etiya.ecommercedemopair7.business.response.orders.GetAllOrderResponse;

import java.util.List;

public interface IOrderService {
    List<GetAllOrderResponse> getAll();
    AddOrderResponse add(AddOrderRequest addOrderRequest);
}
