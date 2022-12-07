package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ICorporateCustomerService;
import com.etiya.ecommercedemopair7.business.request.corporateCustomers.AddCorporateCustomerRequest;
import com.etiya.ecommercedemopair7.business.response.corporateCustomers.AddCorporateCustomerResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.CorporateCustomer;
import com.etiya.ecommercedemopair7.repository.abstracts.ICorporateCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorporateCustomerManager implements ICorporateCustomerService {

    private ICorporateCustomerRepository corporateCustomerRepository;
    private IModelMapperService mapper;

    @Autowired
    public CorporateCustomerManager(ICorporateCustomerRepository corporateCustomerRepository, IModelMapperService mapper) {
        this.corporateCustomerRepository = corporateCustomerRepository;
        this.mapper = mapper;
    }

    @Override
    public AddCorporateCustomerResponse add(AddCorporateCustomerRequest addCorporateCustomerRequest) {

        CorporateCustomer corporateCustomer = mapper.forRequest().map(addCorporateCustomerRequest, CorporateCustomer.class);

        CorporateCustomer savedCorporateCustomer = corporateCustomerRepository.save(corporateCustomer);

        AddCorporateCustomerResponse response = mapper.forResponse().map(savedCorporateCustomer, AddCorporateCustomerResponse.class);

        return response;
    }
}
