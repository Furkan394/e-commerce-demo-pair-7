package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.sellers.GetAllSellerResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISellerRepository extends JpaRepository<Seller,Integer> {
    @Query("Select new com.etiya.ecommercedemopair7.business.response.sellers.GetAllSellerResponse "+
            "(sr.name,sr.number,sr.isVerified) From Seller sr")
    Page<GetAllSellerResponse> findAllSellersWithPage(Pageable pageable);
}
