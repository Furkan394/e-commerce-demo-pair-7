package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.towns.GetAllTownResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Town;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITownRepository extends JpaRepository<Town, Integer> {
    @Query("Select new com.etiya.ecommercedemopair7.business.response.towns.GetAllTownResponse(t.id, t.name,t.city.id) from Town t")
    Page<GetAllTownResponse> findAllTownsWithPage(Pageable pageable);
}
