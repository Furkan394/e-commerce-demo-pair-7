package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ICityService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.cities.GetAllCityResponse;
import com.etiya.ecommercedemopair7.business.response.cities.GetCityResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-all-cities-with-page")
    public ResponseEntity<DataResult<Page<GetAllCityResponse>>> getAllCitiesWithPage(@RequestParam("page") int page,
                                                                                        @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(cityService.getAllCitiesWithPage(pageable));
    }
}
