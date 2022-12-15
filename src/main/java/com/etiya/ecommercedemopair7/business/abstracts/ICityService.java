package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.cities.GetAllCityResponse;
import com.etiya.ecommercedemopair7.business.response.cities.GetCityResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICityService {
    DataResult<List<GetAllCityResponse>> getAll();
    DataResult<GetCityResponse> getById(int cityId);
    DataResult<Page<GetAllCityResponse>> getAllCitiesWithPage(Pageable pageable);
}
