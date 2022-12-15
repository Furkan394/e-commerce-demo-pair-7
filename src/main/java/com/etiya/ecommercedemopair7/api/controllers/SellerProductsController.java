package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ISellerProductService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.sellerProducts.AddSellerProductRequest;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.AddSellerProductResponse;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetAllSellerProductResponse;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetSellerProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<DataResult<List<GetAllSellerProductResponse>>> getAll() {
        return ResponseEntity.ok(sellerProductService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetSellerProductResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(sellerProductService.getById(id));
    }

    @GetMapping("/get-all-seller-products-with-page")
    public ResponseEntity<DataResult<Page<GetAllSellerProductResponse>>> getSellerProductsWithPage
            (@RequestParam("page") int page, @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(sellerProductService.getSellerProductsWithPage(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddSellerProductResponse>> add(@RequestBody @Valid AddSellerProductRequest addSellerProductRequest) {
        return new ResponseEntity<>(sellerProductService.add(addSellerProductRequest), HttpStatus.CREATED);
    }
}
