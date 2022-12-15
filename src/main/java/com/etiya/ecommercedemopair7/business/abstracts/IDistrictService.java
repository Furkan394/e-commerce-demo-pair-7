package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.districts.GetAllDistrictResponse;
import com.etiya.ecommercedemopair7.business.response.districts.GetDistrictResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface IDistrictService {
    DataResult<List<GetAllDistrictResponse>> getAll();
    DataResult<GetDistrictResponse> getById(int districtId);
    DataResult<Slice<GetAllDistrictResponse>> getAllDistrictsWithSlice(Pageable pageable);

}
