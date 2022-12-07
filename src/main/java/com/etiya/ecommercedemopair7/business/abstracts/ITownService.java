package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.towns.GetTownResponse;

public interface ITownService {
    GetTownResponse getById(int townId);
}
