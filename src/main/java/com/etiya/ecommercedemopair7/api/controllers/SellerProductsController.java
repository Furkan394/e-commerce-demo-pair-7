package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ISellerProductService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.sellerProducts.AddSellerProductRequest;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.AddSellerProductResponse;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetAllSellerProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "seller-products")
public class SellerProductsController {

    private ISellerProductService sellerProductService;

    @Autowired
    public SellerProductsController(ISellerProductService sellerProductService) {
        this.sellerProductService = sellerProductService;
    }

    @GetMapping
    public DataResult<List<GetAllSellerProductResponse>> getAll() {
        return sellerProductService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddSellerProductResponse>> add(@RequestBody @Valid AddSellerProductRequest addSellerProductRequest) {
        return new ResponseEntity<>(sellerProductService.add(addSellerProductRequest), HttpStatus.CREATED);
    }
}
