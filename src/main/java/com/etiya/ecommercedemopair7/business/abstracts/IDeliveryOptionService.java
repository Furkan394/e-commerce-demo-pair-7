package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.deliveryOptions.AddDeliveryOptionRequest;
import com.etiya.ecommercedemopair7.business.response.deliveryOptions.AddDeliveryOptionResponse;
import com.etiya.ecommercedemopair7.business.response.deliveryOptions.GetAllDeliveryOptionResponse;
import com.etiya.ecommercedemopair7.business.response.deliveryOptions.GetDeliveryOptionResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.DeliveryOption;

import java.util.List;

public interface IDeliveryOptionService {
    DataResult<List<GetAllDeliveryOptionResponse>> getAll();
    DataResult<GetDeliveryOptionResponse> getById(int deliveryOptionId);
    DataResult<DeliveryOption> getByDeliveryOptionId(int deliveryOptionId);
    DataResult<AddDeliveryOptionResponse> add(AddDeliveryOptionRequest addDeliveryOptionRequest);
}
