package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.streets.GetAllStreetResponse;
import com.etiya.ecommercedemopair7.business.response.streets.GetStreetResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Street;

import java.util.List;

public interface IStreetService {
    List<GetAllStreetResponse> getAll();
    GetStreetResponse getById(int streetId);
    Street getByStreetId(int streetId);
}
