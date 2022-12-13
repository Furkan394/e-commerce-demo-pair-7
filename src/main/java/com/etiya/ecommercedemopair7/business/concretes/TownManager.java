package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ITownService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.response.towns.GetAllTownResponse;
import com.etiya.ecommercedemopair7.business.response.towns.GetTownResponse;
import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Town;
import com.etiya.ecommercedemopair7.repository.abstracts.ITownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownManager implements ITownService {

    private ITownRepository townRepository;
    private IModelMapperService mapper;
    private IMessageSourceService messageSourceService;

    @Autowired
    public TownManager(ITownRepository townRepository, IModelMapperService mapper, IMessageSourceService messageSourceService) {
        this.townRepository = townRepository;
        this.mapper = mapper;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public DataResult<List<GetAllTownResponse>> getAll() {
        List<Town> towns = townRepository.findAll();
        List<GetAllTownResponse> response = towns.stream()
                .map(town -> mapper.forResponse().map(town, GetAllTownResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Town.townsListed));
    }

    @Override
    public DataResult<GetTownResponse> getById(int townId) {
        Town town = checkIfTownExistsById(townId);
        GetTownResponse response = mapper.forResponse().map(town, GetTownResponse.class);
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Town.townReceived));
    }

    private Town checkIfTownExistsById(int id) {
        Town currentTown;
        try {
            currentTown = this.townRepository.findById(id).get();
        } catch (Exception e) {
            throw new BusinessException(messageSourceService.getMessage(Messages.Town.townNotFound));
        }
        return currentTown;
    }
}