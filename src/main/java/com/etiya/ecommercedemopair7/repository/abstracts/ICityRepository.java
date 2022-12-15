package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.cities.GetAllCityResponse;
import com.etiya.ecommercedemopair7.entities.concretes.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICityRepository extends JpaRepository<City, Integer> {
    @Query("select new com.etiya.ecommercedemopair7.business.response.cities.GetAllCityResponse " +
            "(c.id,c.name,c.country.id) from City c")
    Page<GetAllCityResponse> findAllCitiesWithPage(Pageable pageable);
}
