package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.products.AddProductRequest;
import com.etiya.ecommercedemopair7.business.response.products.AddProductResponse;
import com.etiya.ecommercedemopair7.business.response.products.GetAllProductResponse;
import com.etiya.ecommercedemopair7.business.response.products.GetProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "products")
public class ProductsController {

    private IProductService productService;

    @Autowired
    public ProductsController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllProductResponse>>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/pagination")
    public ResponseEntity<DataResult<Page<GetAllProductResponse>>> getAllWithPagination(@RequestParam("page") int page,
                                                                          @RequestParam("Pagesize") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(productService.getAllWithPagination(pageable));
    }
    @GetMapping("/slice")
    public ResponseEntity<DataResult<Slice<Product>>> getAllWithSlice(@RequestParam("page") int page,
                                                                      @RequestParam("Pagesize") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(productService.getAllWithSlice(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetProductResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("get-by-name")
    public ResponseEntity<DataResult<Product>> getByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(productService.getByName(name));
    }

    @GetMapping("custom-get-by-name")
    public ResponseEntity<DataResult<Product>> customGetByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(productService.customGetByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddProductResponse>> add(@RequestBody @Valid AddProductRequest addProductRequest) {
        return new ResponseEntity<>(productService.add(addProductRequest), HttpStatus.CREATED);
    }
}
