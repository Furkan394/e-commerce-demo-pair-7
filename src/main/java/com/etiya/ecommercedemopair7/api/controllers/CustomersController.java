package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ICustomerService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.customers.GetAllCustomerResponse;
import com.etiya.ecommercedemopair7.business.response.customers.GetCustomerResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "customers")
public class CustomersController {

    private ICustomerService customerService;

    @Autowired
    public CustomersController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllCustomerResponse>>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetCustomerResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping("/find-all-customers-with-slice")
    public ResponseEntity<DataResult<Slice<GetAllCustomerResponse>>> getAllCustomersWithSlice(@RequestParam("page") int page,
                                                                                            @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(customerService.getAllCustomersWithSlice(pageable));
    }
}
