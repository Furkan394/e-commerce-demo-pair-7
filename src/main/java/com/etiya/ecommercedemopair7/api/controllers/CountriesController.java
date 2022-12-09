package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ICountryService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.countries.GetAllCountryResponse;
import com.etiya.ecommercedemopair7.business.response.countries.GetCountryResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "countries")
public class CountriesController {

    private ICountryService countryService;

    @Autowired
    public CountriesController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public DataResult<List<GetAllCountryResponse>> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetCountryResponse> getById(@PathVariable int id) {
        return this.countryService.getById(id);
    }
}
