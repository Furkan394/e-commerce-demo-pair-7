package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IPaymentTypeService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.paymentTypes.AddPaymentTypeRequest;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.AddPaymentTypeResponse;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.GetAllPaymentTypeResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.PaymentType;
import com.etiya.ecommercedemopair7.repository.abstracts.IPaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public DataResult<List<GetAllPaymentTypeResponse>> getAll() {
        List<PaymentType> paymentTypes = paymentTypeRepository.findAll();
        List<GetAllPaymentTypeResponse> response = paymentTypes.stream()
                .map(paymentType -> mapper.forResponse().map(paymentType, GetAllPaymentTypeResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, Messages.PaymentType.paymentTypesListed);
    }

    @Override
    public DataResult<AddPaymentTypeResponse> add(AddPaymentTypeRequest addPaymentTypeRequest) {

        PaymentType paymentType = mapper.forRequest().map(addPaymentTypeRequest, PaymentType.class);

        PaymentType savedPaymentType = paymentTypeRepository.save(paymentType);

        AddPaymentTypeResponse response = mapper.forResponse().map(savedPaymentType, AddPaymentTypeResponse.class);

        return new SuccessDataResult<>(response, Messages.PaymentType.paymentTypeAdded);
    }
}
