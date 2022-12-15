package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.deliveryOptions.GetAllDeliveryOptionResponse;
import com.etiya.ecommercedemopair7.entities.concretes.DeliveryOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDeliveryOptionRepository extends JpaRepository<DeliveryOption, Integer> {
    @Query("select new com.etiya.ecommercedemopair7.business.response.deliveryOptions.GetAllDeliveryOptionResponse " +
            "(d.id,d.name) from DeliveryOption d")
    Page<GetAllDeliveryOptionResponse> findAllDeliveryOptionsWithPage(Pageable pageable);
}
