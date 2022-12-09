package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ISellerService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.sellers.AddSellerRequest;
import com.etiya.ecommercedemopair7.business.response.sellers.AddSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetAllSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetSellerResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "sellers")
public class SellersController {

    private ISellerService sellerService;

    @Autowired
    public SellersController(ISellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public DataResult<List<GetAllSellerResponse>> getAll() {
        return sellerService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetSellerResponse> getById(@PathVariable int id) {
        return sellerService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddSellerResponse>> add(@RequestBody AddSellerRequest addSellerRequest) {
        return new ResponseEntity<>(sellerService.add(addSellerRequest), HttpStatus.CREATED);
    }

}
