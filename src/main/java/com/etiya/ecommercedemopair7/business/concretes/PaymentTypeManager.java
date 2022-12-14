package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IPaymentTypeService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.paymentTypes.AddPaymentTypeRequest;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.AddPaymentTypeResponse;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.GetAllPaymentTypeResponse;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.GetPaymentTypeResponse;
import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.PaymentType;
import com.etiya.ecommercedemopair7.repository.abstracts.IPaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentTypeManager implements IPaymentTypeService {

    private IPaymentTypeRepository paymentTypeRepository;
    private IModelMapperService mapper;
    private IMessageSourceService messageSourceService;

    @Autowired
    public PaymentTypeManager(IPaymentTypeRepository paymentTypeRepository,
                              IModelMapperService mapper, IMessageSourceService messageSourceService) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.mapper = mapper;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public DataResult<List<GetAllPaymentTypeResponse>> getAll() {
        List<PaymentType> paymentTypes = paymentTypeRepository.findAll();
        List<GetAllPaymentTypeResponse> response = paymentTypes.stream()
                .map(paymentType -> mapper.forResponse().map(paymentType, GetAllPaymentTypeResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.PaymentType.paymentTypesListed));
    }

    @Override
    public DataResult<AddPaymentTypeResponse> add(AddPaymentTypeRequest addPaymentTypeRequest) {

        PaymentType paymentType = mapper.forRequest().map(addPaymentTypeRequest, PaymentType.class);

        PaymentType savedPaymentType = paymentTypeRepository.save(paymentType);

        AddPaymentTypeResponse response = mapper.forResponse().map(savedPaymentType, AddPaymentTypeResponse.class);

        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.PaymentType.paymentTypeAdded));
    }

    @Override
    public DataResult<GetPaymentTypeResponse> getById(int paymentTypeId) {
        PaymentType paymentType = checkIfPaymentTypeExistsById(paymentTypeId);
        GetPaymentTypeResponse response = mapper.forResponse().map(paymentType, GetPaymentTypeResponse.class);
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.PaymentType.paymentTypeReceived));
    }

    @Override
    public PaymentType getByPaymentTypeId(int paymentTypeId) {
        return checkIfPaymentTypeExistsById(paymentTypeId);
    }

    @Override
    public DataResult<Page<GetAllPaymentTypeResponse>> getAllPaymentTypesWithPage(Pageable pageable) {
        return new SuccessDataResult<>(paymentTypeRepository.findAllPaymentTypesWithPage(pageable),
                messageSourceService.getMessage(Messages.PaymentType.paymentTypesListed));
    }

    private PaymentType checkIfPaymentTypeExistsById(int addressId) {
        PaymentType currentPaymentType;
        try {
            currentPaymentType = this.paymentTypeRepository.findById(addressId).get();
        } catch (Exception e) {
            throw new BusinessException(messageSourceService.getMessage(Messages.PaymentType.paymentTypeNotFound));
        }
        return currentPaymentType;
    }
}
