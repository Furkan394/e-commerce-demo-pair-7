package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ITownService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.towns.GetAllTownResponse;
import com.etiya.ecommercedemopair7.business.response.towns.GetTownResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<DataResult<List<GetAllTownResponse>>> getAll() {
        return ResponseEntity.ok(townService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetTownResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(townService.getById(id));
    }

    @GetMapping("/get-all-towns-with-slice")
    public ResponseEntity<DataResult<Page<GetAllTownResponse>>> getAllTownsWithPage(@RequestParam("page") int page,
                                                                                     @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(townService.getAllTownsWithPage(pageable));
    }
}