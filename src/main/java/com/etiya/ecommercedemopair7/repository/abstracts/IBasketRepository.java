package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.baskets.GetAllBasketResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Basket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBasketRepository extends JpaRepository<Basket, Integer> {
    Basket findByCustomerId(int customerId);

    @Query("select new com.etiya.ecommercedemopair7.business.response.baskets.GetAllBasketResponse " +
            "(b.id,b.totalPrice,b.shippingPrice,b.customer.id) from Basket b")
    Page<GetAllBasketResponse> findAllBasketsWithPage(Pageable pageable);

}
