package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductCharService;
import com.etiya.ecommercedemopair7.business.abstracts.IProductCharValueService;
import com.etiya.ecommercedemopair7.business.request.productCharValues.AddProductCharValueRequest;
import com.etiya.ecommercedemopair7.business.response.productCharValues.AddProductCharValueResponse;
import com.etiya.ecommercedemopair7.business.response.productCharValues.GetAllProductCharValueResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
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

    @Autowired
    ProductCharValueManager(IProductCharValueRepository productCharValueRepository, IProductCharService productCharService, IModelMapperService mapper) {
        this.productCharValueRepository = productCharValueRepository;
        this.productCharService = productCharService;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllProductCharValueResponse> getAll() {
        List<ProductCharValue> productCharValues = productCharValueRepository.findAll();
        List<GetAllProductCharValueResponse> response = productCharValues.stream()
                .map(productCharValue -> mapper.forResponse().map(productCharValue, GetAllProductCharValueResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public AddProductCharValueResponse add(AddProductCharValueRequest addProductCharValueRequest) {

        getProductChar(addProductCharValueRequest);

        ProductCharValue productCharValue = mapper.forRequest().map(addProductCharValueRequest, ProductCharValue.class);

        ProductCharValue savedProductCharValue = productCharValueRepository.save((productCharValue));

        AddProductCharValueResponse response = mapper.forResponse().map(savedProductCharValue, AddProductCharValueResponse.class);

        return response;
    }

    private ProductChar getProductChar(AddProductCharValueRequest addProductCharValueRequest) {
        ProductChar productChar = productCharService.getByProductCharId(addProductCharValueRequest.getProductCharId());
        return productChar;
    }

}
