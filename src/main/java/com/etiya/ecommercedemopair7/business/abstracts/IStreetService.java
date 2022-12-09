package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.streets.GetAllStreetResponse;
import com.etiya.ecommercedemopair7.business.response.streets.GetStreetResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Street;

import java.util.List;

public interface IStreetService {
    DataResult<List<GetAllStreetResponse>> getAll();
    DataResult<GetStreetResponse> getById(int streetId);
    DataResult<Street> getByStreetId(int streetId);
}
