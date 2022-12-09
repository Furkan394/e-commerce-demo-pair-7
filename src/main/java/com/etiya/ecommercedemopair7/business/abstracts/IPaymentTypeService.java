package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.paymentTypes.AddPaymentTypeRequest;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.AddPaymentTypeResponse;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.GetAllPaymentTypeResponse;

import java.util.List;

public interface IPaymentTypeService {
    List<GetAllPaymentTypeResponse> getAll();
    AddPaymentTypeResponse add(AddPaymentTypeRequest addPaymentTypeRequest);
}
