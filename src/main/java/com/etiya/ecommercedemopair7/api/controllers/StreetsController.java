package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IStreetService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.streets.GetAllStreetResponse;
import com.etiya.ecommercedemopair7.business.response.streets.GetStreetResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-all-streets-with")
    public ResponseEntity<DataResult<Page<GetAllStreetResponse>>> getAllStreetsWithPage(@RequestParam("page") int page,
                                                                                @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(streetService.getAllStreetsWithPage(pageable));
    }
    }


