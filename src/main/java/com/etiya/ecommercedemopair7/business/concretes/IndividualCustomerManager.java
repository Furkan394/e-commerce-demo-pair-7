package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IIndividualCustomerService;
import com.etiya.ecommercedemopair7.business.request.individualCustomers.AddIndividualCustomerRequest;
import com.etiya.ecommercedemopair7.business.response.individualCustomers.AddIndividualCustomerResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.IndividualCustomer;
import com.etiya.ecommercedemopair7.repository.abstracts.IIndividualCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndividualCustomerManager implements IIndividualCustomerService {

    private IIndividualCustomerRepository individualCustomerRepository;
    private IModelMapperService mapper;

    @Autowired
    IndividualCustomerManager(IIndividualCustomerRepository individualCustomerRepository, IModelMapperService mapper) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.mapper = mapper;
    }

    @Override
    public AddIndividualCustomerResponse add(AddIndividualCustomerRequest addIndividualCustomerRequest) {

        IndividualCustomer individualCustomer = mapper.forRequest().map(addIndividualCustomerRequest, IndividualCustomer.class);

        IndividualCustomer savedIndividualCustomer = individualCustomerRepository.save(individualCustomer);

        AddIndividualCustomerResponse response = mapper.forResponse().map(savedIndividualCustomer, AddIndividualCustomerResponse.class);

        return response;
    }
}
