package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.towns.GetAllTownResponse;
import com.etiya.ecommercedemopair7.business.response.towns.GetTownResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITownService {
    DataResult<List<GetAllTownResponse>> getAll();
    DataResult<GetTownResponse> getById(int townId);
    DataResult<Page<GetAllTownResponse>> getAllTownsWithPage(Pageable pageable);
}
