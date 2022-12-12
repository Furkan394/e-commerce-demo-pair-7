package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IInvoiceService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.invoices.AddInvoiceRequest;
import com.etiya.ecommercedemopair7.business.response.invoices.AddInvoiceResponse;
import com.etiya.ecommercedemopair7.business.response.invoices.GetAllInvoiceResponse;
import com.etiya.ecommercedemopair7.business.response.invoices.GetInvoiceResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "invoices")
public class InvoicesController {
    private IInvoiceService invoiceService;

    @Autowired
    public InvoicesController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllInvoiceResponse>>> getAll() {
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetInvoiceResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(invoiceService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddInvoiceResponse>> add(@RequestBody @Valid AddInvoiceRequest addInvoiceRequest) {
        return new ResponseEntity<>(invoiceService.add(addInvoiceRequest), HttpStatus.CREATED);
    }

}
