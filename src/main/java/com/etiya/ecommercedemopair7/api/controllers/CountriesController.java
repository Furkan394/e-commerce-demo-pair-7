package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ICountryService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.countries.GetAllCountryResponse;
import com.etiya.ecommercedemopair7.business.response.countries.GetCountryResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DataResult<List<GetAllCountryResponse>>> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetCountryResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(countryService.getById(id));
    }

    @GetMapping("/get-all-countries-with-page")
    public ResponseEntity<DataResult<Page<GetAllCountryResponse>>> getAllCountriesWithPage(@RequestParam("page") int page,
                                                                                           @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(countryService.getAllCountriesWithPage(pageable));
    }
}
