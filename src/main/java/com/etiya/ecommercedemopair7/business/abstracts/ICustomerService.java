package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.customers.GetAllCustomerResponse;
import com.etiya.ecommercedemopair7.business.response.customers.GetCustomerResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Customer;

import java.util.List;

public interface ICustomerService {
    List<GetAllCustomerResponse> getAll();
    GetCustomerResponse getById(int customerId);
    Customer getByCustomerId(int customerId);
}
