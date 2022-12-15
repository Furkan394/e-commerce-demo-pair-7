package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ISellerService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.sellers.AddSellerRequest;
import com.etiya.ecommercedemopair7.business.response.productCharValues.GetAllProductCharValueResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.AddSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetAllSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetSellerResponse;
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
@RequestMapping(Paths.apiPrefix + "sellers")
public class SellersController {

    private ISellerService sellerService;

    @Autowired
    public SellersController(ISellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllSellerResponse>>> getAll() {
        return ResponseEntity.ok(sellerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetSellerResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(sellerService.getById(id));
    }

    @GetMapping("/get-all-sellers-with-page")
    public ResponseEntity<DataResult<Page<GetAllSellerResponse>>> getSellersWithPage
            (@RequestParam("page") int page, @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(sellerService.getSellersWithPage(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddSellerResponse>> add(@RequestBody AddSellerRequest addSellerRequest) {
        return new ResponseEntity<>(sellerService.add(addSellerRequest), HttpStatus.CREATED);
    }

}
