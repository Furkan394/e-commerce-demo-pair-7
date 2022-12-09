package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.districts.GetAllDistrictResponse;
import com.etiya.ecommercedemopair7.business.response.districts.GetDistrictResponse;

import java.util.List;

public interface IDistrictService {
    List<GetAllDistrictResponse> getAll();
    GetDistrictResponse getById(int districtId);
}
