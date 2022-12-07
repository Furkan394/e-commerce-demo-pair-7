package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ITownService;
import com.etiya.ecommercedemopair7.business.response.towns.GetTownResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Town;
import com.etiya.ecommercedemopair7.repository.abstracts.ITownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownManager implements ITownService {

    private ITownRepository townRepository;
    private IModelMapperService mapper;

    @Autowired
    public TownManager(ITownRepository townRepository, IModelMapperService mapper) {
        this.townRepository = townRepository;
        this.mapper = mapper;
    }

    @Override
    public GetTownResponse getById(int townId) {
        Town town = checkIfTownExistsById(townId);
        GetTownResponse response = mapper.forResponse().map(town, GetTownResponse.class);
        return response;
    }

    private Town checkIfTownExistsById(int id) {
        Town currentTown = this.townRepository.findById(id).orElseThrow();
        return currentTown;
    }
}


