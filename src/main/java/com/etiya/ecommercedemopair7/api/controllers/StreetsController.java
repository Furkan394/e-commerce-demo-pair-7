package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IStreetService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.streets.GetAllStreetResponse;
import com.etiya.ecommercedemopair7.business.response.streets.GetStreetResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "streets")
public class StreetsController {

    private IStreetService streetService;

    @Autowired
    public StreetsController(IStreetService streetService) {
        this.streetService = streetService;
    }


    @GetMapping
    public ResponseEntity<DataResult<List<GetAllStreetResponse>>> getAll() {
        return ResponseEntity.ok(streetService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetStreetResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(streetService.getById(id));
    }
}

