package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IStreetService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.response.streets.GetAllStreetResponse;
import com.etiya.ecommercedemopair7.business.response.streets.GetStreetResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Street;
import com.etiya.ecommercedemopair7.repository.abstracts.IStreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreetManager implements IStreetService {
    private IStreetRepository streetRepository;
    private IModelMapperService mapper;

    @Autowired
    StreetManager(IStreetRepository streetRepository, IModelMapperService mapper) {
        this.streetRepository = streetRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllStreetResponse> getAll() {
        List<Street> streets = streetRepository.findAll();
        List<GetAllStreetResponse> response = streets.stream()
                .map(street -> mapper.forResponse().map(street, GetAllStreetResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GetStreetResponse getById(int streetId) {
        Street seller = checkIfStreetExistsById(streetId);
        GetStreetResponse response = mapper.forResponse().map(seller, GetStreetResponse.class);
        return response;

    }

    @Override
    public Street getByStreetId(int streetId) {
        return checkIfStreetExistsById(streetId);
    }

    private Street checkIfStreetExistsById(int id) {
        Street currentStreet;
        try {
            currentStreet = this.streetRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.Street.streetNotFound);
        }
        return currentStreet;
    }
}
