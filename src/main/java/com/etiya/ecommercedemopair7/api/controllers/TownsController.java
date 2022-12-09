package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ITownService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.towns.GetAllTownResponse;
import com.etiya.ecommercedemopair7.business.response.towns.GetTownResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "towns")
public class TownsController {

    private ITownService townService;

    @Autowired
    public TownsController(ITownService townService) {
        this.townService = townService;
    }

    @GetMapping
    public DataResult<List<GetAllTownResponse>> getAll() {
        return townService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetTownResponse> getById(@PathVariable int id) {
        return this.townService.getById(id);
    }
}