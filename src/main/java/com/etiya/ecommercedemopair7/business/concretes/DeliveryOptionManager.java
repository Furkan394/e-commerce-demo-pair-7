package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IDeliveryOptionService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.deliveryOptions.AddDeliveryOptionRequest;
import com.etiya.ecommercedemopair7.business.response.deliveryOptions.AddDeliveryOptionResponse;
import com.etiya.ecommercedemopair7.business.response.deliveryOptions.GetAllDeliveryOptionResponse;
import com.etiya.ecommercedemopair7.business.response.deliveryOptions.GetDeliveryOptionResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.DeliveryOption;
import com.etiya.ecommercedemopair7.repository.abstracts.IDeliveryOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryOptionManager implements IDeliveryOptionService {

    private IDeliveryOptionRepository deliveryOptionRepository;
    private IModelMapperService mapper;

    @Autowired
    public DeliveryOptionManager(IDeliveryOptionRepository deliveryOptionRepository, IModelMapperService mapper) {
        this.deliveryOptionRepository = deliveryOptionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllDeliveryOptionResponse> getAll() {
        List<DeliveryOption> deliveryOptions = deliveryOptionRepository.findAll();
        List<GetAllDeliveryOptionResponse> response = deliveryOptions.stream()
                .map(deliveryOption -> mapper.forResponse().map(deliveryOption, GetAllDeliveryOptionResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GetDeliveryOptionResponse getById(int deliveryOptionId) {
        DeliveryOption deliveryOption = getByDeliveryOptionId(deliveryOptionId);
        GetDeliveryOptionResponse response = mapper.forResponse().map(deliveryOption, GetDeliveryOptionResponse.class);
        return response;
    }

    @Override
    public DeliveryOption getByDeliveryOptionId(int deliveryOptionId) {
        return checkIfDeliveryOptionExistsById(deliveryOptionId);
    }

    @Override
    public AddDeliveryOptionResponse add(AddDeliveryOptionRequest addDeliveryOptionRequest) {
        DeliveryOption deliveryOption = mapper.forRequest().map(addDeliveryOptionRequest, DeliveryOption.class);

        DeliveryOption savedDeliveryOption = deliveryOptionRepository.save(deliveryOption);

        AddDeliveryOptionResponse response = mapper.forResponse().map(savedDeliveryOption, AddDeliveryOptionResponse.class);

        return response;
    }

    private DeliveryOption checkIfDeliveryOptionExistsById(int deliveryOptionId) {
        DeliveryOption currentDeliveryOption;
        try {
            currentDeliveryOption = deliveryOptionRepository.findById(deliveryOptionId).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.DeliveryOption.deliveryOptionNotFound);
        }
        return currentDeliveryOption;
    }
}
