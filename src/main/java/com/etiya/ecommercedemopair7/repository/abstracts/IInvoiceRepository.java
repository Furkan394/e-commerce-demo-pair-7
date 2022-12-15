package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.invoices.GetAllInvoiceResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IInvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query("select new com.etiya.ecommercedemopair7.business.response.invoices.GetAllInvoiceResponse" +
            "(i.id, i.number, i.createdDate, i.order.id) from Invoice i")
    Slice<GetAllInvoiceResponse> findAllInvoicesWithSlice(Pageable pageable);
}
