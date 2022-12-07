package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.districts.GetDistrictResponse;

public interface IDistrictService {
    GetDistrictResponse getById(int districtId);
}
