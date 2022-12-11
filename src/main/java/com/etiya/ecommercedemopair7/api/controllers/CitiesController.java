package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ICityService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.cities.GetAllCityResponse;
import com.etiya.ecommercedemopair7.business.response.cities.GetCityResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "cities")
public class CitiesController {

    private ICityService cityService;

    @Autowired
    public CitiesController(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllCityResponse>>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetCityResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(cityService.getById(id));
    }
}
