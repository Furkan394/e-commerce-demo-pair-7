package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IProductCharService;

import com.etiya.ecommercedemopair7.business.request.productChars.AddProductCharRequest;
import com.etiya.ecommercedemopair7.business.response.productChars.AddProductCharResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import com.etiya.ecommercedemopair7.entities.concretes.ProductChar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-chars")
public class ProductCharsController {
    private IProductCharService productCharService;

    @Autowired
    public ProductCharsController(IProductCharService productCharService) {
        this.productCharService = productCharService;
    }

    @GetMapping("/{id}")
    public ProductChar getById(@PathVariable int id){
        return productCharService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<AddProductCharResponse> add(@RequestBody AddProductCharRequest addProductCharRequest) {
        return new ResponseEntity<AddProductCharResponse>(productCharService.add(addProductCharRequest), HttpStatus.CREATED);
    }
}

