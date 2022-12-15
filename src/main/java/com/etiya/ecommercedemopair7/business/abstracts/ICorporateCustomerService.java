package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.corporateCustomers.AddCorporateCustomerRequest;
import com.etiya.ecommercedemopair7.business.response.corporateCustomers.AddCorporateCustomerResponse;
import com.etiya.ecommercedemopair7.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ICorporateCustomerService {
    DataResult<List<GetAllCorporateCustomerResponse>> getAll();
    DataResult<AddCorporateCustomerResponse> add(AddCorporateCustomerRequest addCorporateCustomerRequest);
    DataResult<Slice<GetAllCorporateCustomerResponse>> getAllCorporateCustomersWithSlice(Pageable pageable);

}
