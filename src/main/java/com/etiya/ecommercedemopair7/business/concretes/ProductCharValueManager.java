package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductCharService;
import com.etiya.ecommercedemopair7.business.abstracts.IProductCharValueService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.productCharValues.AddProductCharValueRequest;
import com.etiya.ecommercedemopair7.business.response.productCharValues.AddProductCharValueResponse;
import com.etiya.ecommercedemopair7.business.response.productCharValues.GetAllProductCharValueResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.ProductChar;
import com.etiya.ecommercedemopair7.entities.concretes.ProductCharValue;
import com.etiya.ecommercedemopair7.repository.abstracts.IProductCharValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCharValueManager implements IProductCharValueService {

    private IProductCharValueRepository productCharValueRepository;
    private IProductCharService productCharService;
    private IModelMapperService mapper;
    private IMessageSourceService messageSourceService;

    @Autowired
    ProductCharValueManager(IProductCharValueRepository productCharValueRepository,
                            IProductCharService productCharService, IModelMapperService mapper,
                            IMessageSourceService messageSourceService) {
        this.productCharValueRepository = productCharValueRepository;
        this.productCharService = productCharService;
        this.mapper = mapper;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public DataResult<List<GetAllProductCharValueResponse>> getAll() {
        List<ProductCharValue> productCharValues = productCharValueRepository.findAll();
        List<GetAllProductCharValueResponse> response = productCharValues.stream()
                .map(productCharValue -> mapper.forResponse().map(productCharValue, GetAllProductCharValueResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.ProductCharValue.productCharValuesListed));
    }

    @Override
    public DataResult<AddProductCharValueResponse> add(AddProductCharValueRequest addProductCharValueRequest) {

        getProductChar(addProductCharValueRequest);

        ProductCharValue productCharValue = mapper.forRequest().map(addProductCharValueRequest, ProductCharValue.class);

        ProductCharValue savedProductCharValue = productCharValueRepository.save((productCharValue));

        AddProductCharValueResponse response = mapper.forResponse().map(savedProductCharValue, AddProductCharValueResponse.class);

        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.ProductCharValue.productCharValueAdded));
    }

    private DataResult<ProductChar> getProductChar(AddProductCharValueRequest addProductCharValueRequest) {
        DataResult<ProductChar> productChar = productCharService.getByProductCharId(addProductCharValueRequest.getProductCharId());
        return productChar;
    }

}
