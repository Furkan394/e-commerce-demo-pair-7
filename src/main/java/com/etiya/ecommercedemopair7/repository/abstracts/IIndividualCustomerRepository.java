package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.individualCustomers.GetAllIndividualCustomerResponse;
import com.etiya.ecommercedemopair7.entities.concretes.IndividualCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IIndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer> {
    @Query("select new com.etiya.ecommercedemopair7.business.response.individualCustomers.GetAllIndividualCustomerResponse " +
            "(i.number,i.firstName,i.lastName,i.identity,i.birthDate) from IndividualCustomer i")
    Page<GetAllIndividualCustomerResponse> findAllIndividualCustomersWithPage(Pageable pageable);
}
