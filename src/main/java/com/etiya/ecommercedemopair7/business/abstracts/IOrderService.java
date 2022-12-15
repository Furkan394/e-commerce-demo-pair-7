package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.orders.AddOrderRequest;
import com.etiya.ecommercedemopair7.business.response.orders.AddOrderResponse;
import com.etiya.ecommercedemopair7.business.response.orders.GetAllOrderResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Order;
import com.etiya.ecommercedemopair7.entities.dtos.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    DataResult<List<GetAllOrderResponse>> getAll();
    DataResult<AddOrderResponse> add(AddOrderRequest addOrderRequest);
    DataResult<List<OrderDto>> getOrderDto();
    Order getByOrderId(int orderId);
    DataResult<Page<GetAllOrderResponse>> getAllOrdersWithPage(Pageable pageable);

}
