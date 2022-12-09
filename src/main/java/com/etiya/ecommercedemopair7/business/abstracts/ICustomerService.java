package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.customers.GetAllCustomerResponse;
import com.etiya.ecommercedemopair7.business.response.customers.GetCustomerResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Customer;

import java.util.List;

public interface ICustomerService {
    DataResult<List<GetAllCustomerResponse>> getAll();
    DataResult<GetCustomerResponse> getById(int customerId);
    DataResult<Customer> getByCustomerId(int customerId);
}
