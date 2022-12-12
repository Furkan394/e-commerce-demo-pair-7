package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IInvoiceService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.invoices.AddInvoiceRequest;
import com.etiya.ecommercedemopair7.business.response.invoices.AddInvoiceResponse;
import com.etiya.ecommercedemopair7.business.response.invoices.GetAllInvoiceResponse;
import com.etiya.ecommercedemopair7.business.response.invoices.GetInvoiceResponse;
import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Invoice;
import com.etiya.ecommercedemopair7.repository.abstracts.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceManager implements IInvoiceService {

    private IInvoiceRepository invoiceRepository;
    private IModelMapperService mapper;

    @Autowired
    public InvoiceManager(IInvoiceRepository invoiceRepository, IModelMapperService mapper) {
        this.invoiceRepository = invoiceRepository;
        this.mapper = mapper;
    }

    @Override
    public DataResult<List<GetAllInvoiceResponse>> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<GetAllInvoiceResponse> response = invoices.stream()
                .map(invoice -> mapper.forResponse().map(invoice, GetAllInvoiceResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, Messages.Invoice.invoicesListed);
    }

    @Override
    public DataResult<GetInvoiceResponse> getById(int invoiceId) {
        Invoice invoice = checkIfInvoiceExistsById(invoiceId);
        GetInvoiceResponse response = mapper.forResponse().map(invoice, GetInvoiceResponse.class);
        return new SuccessDataResult<>(response, Messages.Invoice.invoiceReceived);
    }

    @Override
    public DataResult<Invoice> getByInvoiceId(int invoiceId) {
        return new SuccessDataResult<>(checkIfInvoiceExistsById(invoiceId));
    }

    @Override
    public DataResult<AddInvoiceResponse> add(AddInvoiceRequest addInvoiceRequest) {
        Invoice invoice = mapper.forRequest().map(addInvoiceRequest, Invoice.class);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        AddInvoiceResponse response = mapper.forResponse().map(savedInvoice, AddInvoiceResponse.class);

        return new SuccessDataResult<>(response, Messages.Invoice.invoiceAdded);
    }

    private Invoice checkIfInvoiceExistsById(int invoiceId) {
        Invoice currentInvoice;
        try {
            currentInvoice = invoiceRepository.findById(invoiceId).get();
        } catch (Exception e) {
            throw new BusinessException(Messages.Invoice.invoiceNotFound);
        }
        return currentInvoice;
    }
}
