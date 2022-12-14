package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IProductCharService;

import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.productChars.AddProductCharRequest;
import com.etiya.ecommercedemopair7.business.response.productChars.AddProductCharResponse;
import com.etiya.ecommercedemopair7.business.response.productChars.GetAllProductCharResponse;
import com.etiya.ecommercedemopair7.business.response.productChars.GetProductCharResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "product-chars")
public class ProductCharsController {
    private IProductCharService productCharService;

    @Autowired
    public ProductCharsController(IProductCharService productCharService) {
        this.productCharService = productCharService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllProductCharResponse>>> getAll() {
        return ResponseEntity.ok(productCharService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetProductCharResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(productCharService.getById(id));
    }
    @GetMapping("/get-all-product-chars-with-page")
    public ResponseEntity<DataResult<Page<GetAllProductCharResponse>>> getAllProductCharsWithPage
            (@RequestParam("page") int page, @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(productCharService.getAllProductCharsWithPage(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddProductCharResponse>> add(@RequestBody AddProductCharRequest addProductCharRequest) {
        return new ResponseEntity<>(productCharService.add(addProductCharRequest), HttpStatus.CREATED);
    }
}

