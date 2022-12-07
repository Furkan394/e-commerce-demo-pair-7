package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IPaymentTypeService;
import com.etiya.ecommercedemopair7.business.request.paymentTypes.AddPaymentTypeRequest;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.AddPaymentTypeResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.PaymentType;
import com.etiya.ecommercedemopair7.repository.abstracts.IPaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentTypeManager implements IPaymentTypeService {

    private IPaymentTypeRepository paymentTypeRepository;
    private IModelMapperService mapper;

    @Autowired
    public PaymentTypeManager(IPaymentTypeRepository paymentTypeRepository, IModelMapperService mapper) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.mapper = mapper;
    }

    @Override
    public AddPaymentTypeResponse add(AddPaymentTypeRequest addPaymentTypeRequest) {

        PaymentType paymentType = mapper.forRequest().map(addPaymentTypeRequest, PaymentType.class);

        PaymentType savedPaymentType = paymentTypeRepository.save(paymentType);

        AddPaymentTypeResponse response = mapper.forResponse().map(savedPaymentType, AddPaymentTypeResponse.class);

        return response;
    }
}
