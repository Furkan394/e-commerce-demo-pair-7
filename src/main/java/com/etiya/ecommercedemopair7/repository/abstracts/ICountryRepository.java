package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.countries.GetAllCountryResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICountryRepository extends JpaRepository<Country, Integer> {
    @Query("select new com.etiya.ecommercedemopair7.business.response.countries.GetAllCountryResponse " +
            "(c.id,c.name) from Country c")
    Page<GetAllCountryResponse> findAllCountriesWithPage(Pageable pageable);
}
