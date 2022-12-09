package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductCharService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.productChars.AddProductCharRequest;
import com.etiya.ecommercedemopair7.business.response.productChars.AddProductCharResponse;
import com.etiya.ecommercedemopair7.business.response.productChars.GetAllProductCharResponse;
import com.etiya.ecommercedemopair7.business.response.productChars.GetProductCharResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.ProductChar;
import com.etiya.ecommercedemopair7.repository.abstracts.IProductCharRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCharManager implements IProductCharService {
    private IProductCharRepository productCharRepository;
    private IModelMapperService mapper;

    @Autowired
    public ProductCharManager(IProductCharRepository productcharRepository, IModelMapperService mapper) {
        this.productCharRepository = productcharRepository;
        this.mapper = mapper;
    }


    @Override
    public List<GetAllProductCharResponse> getAll() {
        List<ProductChar> productChars = productCharRepository.findAll();
        List<GetAllProductCharResponse> response = productChars.stream()
                .map(productChar -> mapper.forResponse().map(productChar, GetAllProductCharResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GetProductCharResponse getById(int productCharId) {
        ProductChar productChar = getByProductCharId(productCharId);
        GetProductCharResponse response = mapper.forResponse().map(productChar, GetProductCharResponse.class);
        return response;
    }

    @Override
    public ProductChar getByProductCharId(int productCharId) {
        return checkIfProductCharExistsById(productCharId);
    }

    @Override
    public AddProductCharResponse add(AddProductCharRequest addProductCharRequest) {

        ProductChar productChar = mapper.forRequest().map(addProductCharRequest, ProductChar.class);

        ProductChar savedProductChar = productCharRepository.save(productChar);

        AddProductCharResponse response = mapper.forResponse().map(savedProductChar, AddProductCharResponse.class);

        return response;

    }

    private ProductChar checkIfProductCharExistsById(int productCharId) {
        ProductChar currentProductChar;
        try {
            currentProductChar = productCharRepository.findById(productCharId).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.ProductChar.ProductCharNotFound);
        }
        return currentProductChar;
    }
}
