package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.corporateCustomers.AddCorporateCustomerRequest;
import com.etiya.ecommercedemopair7.business.response.corporateCustomers.AddCorporateCustomerResponse;
import com.etiya.ecommercedemopair7.business.response.corporateCustomers.GetAllCorporateCustomerResponse;

import java.util.List;

public interface ICorporateCustomerService {
    List<GetAllCorporateCustomerResponse> getAll();
    AddCorporateCustomerResponse add(AddCorporateCustomerRequest addCorporateCustomerRequest);
}
