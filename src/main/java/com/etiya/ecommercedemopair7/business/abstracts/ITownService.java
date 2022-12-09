package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.towns.GetAllTownResponse;
import com.etiya.ecommercedemopair7.business.response.towns.GetTownResponse;

import java.util.List;

public interface ITownService {
    List<GetAllTownResponse> getAll();
    GetTownResponse getById(int townId);
}
