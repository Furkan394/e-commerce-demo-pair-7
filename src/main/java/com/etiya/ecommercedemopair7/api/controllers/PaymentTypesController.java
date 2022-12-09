package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IPaymentTypeService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.paymentTypes.AddPaymentTypeRequest;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.AddPaymentTypeResponse;
import com.etiya.ecommercedemopair7.business.response.paymentTypes.GetAllPaymentTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "payment-types")
public class PaymentTypesController {

    private IPaymentTypeService paymentTypeService;

    @Autowired
    public PaymentTypesController(IPaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @GetMapping
    public List<GetAllPaymentTypeResponse> getAll() {
        return paymentTypeService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<AddPaymentTypeResponse> add(@RequestBody AddPaymentTypeRequest addPaymentTypeRequest) {
        return new ResponseEntity<AddPaymentTypeResponse>(paymentTypeService.add(addPaymentTypeRequest), HttpStatus.CREATED);
    }
}
