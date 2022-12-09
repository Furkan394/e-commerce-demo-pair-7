package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IProductCharValueService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.productCharValues.AddProductCharValueRequest;
import com.etiya.ecommercedemopair7.business.response.productCharValues.AddProductCharValueResponse;
import com.etiya.ecommercedemopair7.business.response.productCharValues.GetAllProductCharValueResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "product-char-values")
public class ProductCharValuesController {
    private IProductCharValueService productCharValueService;

    @Autowired
    public ProductCharValuesController(IProductCharValueService productCharValueService) {
        this.productCharValueService = productCharValueService;
    }

    @GetMapping
    public DataResult<List<GetAllProductCharValueResponse>> getAll() {
        return productCharValueService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddProductCharValueResponse>> add(@RequestBody AddProductCharValueRequest addProductCharValueRequest) {
        return new ResponseEntity<>(productCharValueService.add(addProductCharValueRequest), HttpStatus.CREATED);
    }


}
