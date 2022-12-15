package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.paymentTypes.GetAllPaymentTypeResponse;
import com.etiya.ecommercedemopair7.entities.concretes.PaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
    @Query("select new com.etiya.ecommercedemopair7.business.response.paymentTypes.GetAllPaymentTypeResponse " +
            "(pt.id,pt.name) from PaymentType pt")
    Page<GetAllPaymentTypeResponse> findAllPaymentTypesWithPage(Pageable pageable);
}
