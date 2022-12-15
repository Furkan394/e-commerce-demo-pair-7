package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.streets.GetAllStreetResponse;
import com.etiya.ecommercedemopair7.business.response.streets.GetStreetResponse;
import com.etiya.ecommercedemopair7.business.response.towns.GetAllTownResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Street;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStreetService {
    DataResult<List<GetAllStreetResponse>> getAll();
    DataResult<GetStreetResponse> getById(int streetId);
    DataResult<Street> getByStreetId(int streetId);
    DataResult<Page<GetAllStreetResponse>> getAllStreetsWithPage(Pageable pageable);

}
