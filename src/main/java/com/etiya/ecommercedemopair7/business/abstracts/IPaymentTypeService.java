package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.paymentTypes.AddPaymentTypeRequest;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.AddPaymentTypeResponse;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.GetAllPaymentTypeResponse;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.GetPaymentTypeResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.PaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPaymentTypeService {
    DataResult<List<GetAllPaymentTypeResponse>> getAll();
    DataResult<AddPaymentTypeResponse> add(AddPaymentTypeRequest addPaymentTypeRequest);
    DataResult<GetPaymentTypeResponse> getById(int paymentTypeId);
    PaymentType getByPaymentTypeId(int paymentTypeId);
    DataResult<Page<GetAllPaymentTypeResponse>> getAllPaymentTypesWithPage(Pageable pageable);
}
