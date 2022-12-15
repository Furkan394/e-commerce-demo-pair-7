package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.etiya.ecommercedemopair7.entities.concretes.CorporateCustomer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICorporateCustomerRepository extends JpaRepository<CorporateCustomer, Integer> {
    @Query("select new com.etiya.ecommercedemopair7.business.response.corporateCustomers.GetAllCorporateCustomerResponse " +
            "(c.number,c.name,c.taxNumber) from CorporateCustomer c")
    Slice<GetAllCorporateCustomerResponse> findAllCorporateCustomersWithSlice(Pageable pageable);
}
