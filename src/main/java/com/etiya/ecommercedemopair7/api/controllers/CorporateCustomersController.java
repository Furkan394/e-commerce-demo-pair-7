package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ICorporateCustomerService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.corporateCustomers.AddCorporateCustomerRequest;
import com.etiya.ecommercedemopair7.business.response.corporateCustomers.AddCorporateCustomerResponse;
import com.etiya.ecommercedemopair7.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "corporate-customers")
public class CorporateCustomersController {

    private ICorporateCustomerService corporateCustomerService;

    public CorporateCustomersController(ICorporateCustomerService corporateCustomerService) {
        this.corporateCustomerService = corporateCustomerService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllCorporateCustomerResponse>>> getAll() {
        return ResponseEntity.ok(corporateCustomerService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddCorporateCustomerResponse>> add(@RequestBody AddCorporateCustomerRequest addCorporateCustomerRequest) {
        return new ResponseEntity<>(corporateCustomerService.add(addCorporateCustomerRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get-all-corporate-customers-with-slice")
    public ResponseEntity<DataResult<Slice<GetAllCorporateCustomerResponse>>> getAllCorporateCustomersWithSlice(@RequestParam("page") int page,
                                                                                                                @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(corporateCustomerService.getAllCorporateCustomersWithSlice(pageable));
    }
}
