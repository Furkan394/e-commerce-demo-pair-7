package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.districts.GetAllDistrictResponse;
import com.etiya.ecommercedemopair7.entities.concretes.District;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDistrictRepository extends JpaRepository<District, Integer> {
    @Query("select new com.etiya.ecommercedemopair7.business.response.districts.GetAllDistrictResponse " +
            "(d.id,d.name,d.town.id) from District d")
    Slice<GetAllDistrictResponse> findAllDistrictsWithSlice(Pageable pageable);
}
