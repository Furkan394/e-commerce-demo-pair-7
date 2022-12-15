package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.streets.GetAllStreetResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Street;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IStreetRepository extends JpaRepository<Street, Integer> {
    @Query("Select new com.etiya.ecommercedemopair7.business.response.streets.GetAllStreetResponse(s.id,s.name,s.district.id) from Street s")
    Page<GetAllStreetResponse> findAllStreetsWithPage(Pageable pageable);
}
