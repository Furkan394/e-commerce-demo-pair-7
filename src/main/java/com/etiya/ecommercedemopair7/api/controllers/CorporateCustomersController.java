package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ICorporateCustomerService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.corporateCustomers.AddCorporateCustomerRequest;
import com.etiya.ecommercedemopair7.business.response.corporateCustomers.AddCorporateCustomerResponse;
import com.etiya.ecommercedemopair7.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
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
    public List<GetAllCorporateCustomerResponse> getAll() {
        return corporateCustomerService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<AddCorporateCustomerResponse> add(@RequestBody AddCorporateCustomerRequest addCorporateCustomerRequest) {
        return new ResponseEntity<AddCorporateCustomerResponse>(corporateCustomerService.add(addCorporateCustomerRequest), HttpStatus.CREATED);
    }
}
