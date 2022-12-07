package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ICustomerService;
import com.etiya.ecommercedemopair7.business.response.customers.GetCustomerResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Customer;
import com.etiya.ecommercedemopair7.repository.abstracts.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements ICustomerService {

    private ICustomerRepository customerRepository;
    private IModelMapperService mapper;

    @Autowired
    public CustomerManager(ICustomerRepository customerRepository, IModelMapperService mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public GetCustomerResponse getById(int customerId) {
        Customer customer = checkIfCustomerExistsById(customerId);
        GetCustomerResponse response = mapper.forResponse().map(customer, GetCustomerResponse.class);
        return response;
    }

    @Override
    public Customer getByCustomerId(int customerId) {
        return checkIfCustomerExistsById(customerId);
    }

    private Customer checkIfCustomerExistsById(int id) {
        Customer currentCustomer;
        try {
            currentCustomer = this.customerRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("Böyle bir müşteri yok.");
        }
        return currentCustomer;
    }
}
