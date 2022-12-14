package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.individualCustomers.AddIndividualCustomerRequest;
import com.etiya.ecommercedemopair7.business.response.individualCustomers.AddIndividualCustomerResponse;
import com.etiya.ecommercedemopair7.business.response.individualCustomers.GetAllIndividualCustomerResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IIndividualCustomerService {
    DataResult<List<GetAllIndividualCustomerResponse>> getAll();
    DataResult<AddIndividualCustomerResponse> add(AddIndividualCustomerRequest addIndividualCustomerRequest);
    DataResult<Page<GetAllIndividualCustomerResponse>> getAllIndividualCustomersWithPage(Pageable pageable);

}
