package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.invoices.AddInvoiceRequest;
import com.etiya.ecommercedemopair7.business.response.invoices.AddInvoiceResponse;
import com.etiya.ecommercedemopair7.business.response.invoices.GetAllInvoiceResponse;
import com.etiya.ecommercedemopair7.business.response.invoices.GetInvoiceResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Invoice;

import java.util.List;

public interface IInvoiceService {
    DataResult<List<GetAllInvoiceResponse>> getAll();
    DataResult<GetInvoiceResponse> getById(int invoiceId);
    DataResult<Invoice> getByInvoiceId(int invoiceId);
    DataResult<AddInvoiceResponse> add(AddInvoiceRequest addInvoiceRequest);
}
