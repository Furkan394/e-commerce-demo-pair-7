package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.customers.GetAllCustomerResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select new com.etiya.ecommercedemopair7.business.response.customers.GetAllCustomerResponse" +
            "(c.number ) from Customer c")
    Slice<GetAllCustomerResponse> findAllCustomersWithSlice(Pageable pageable);
}
