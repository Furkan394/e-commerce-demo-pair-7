package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.basketItems.GetAllBasketItemResponse;
import com.etiya.ecommercedemopair7.entities.concretes.BasketItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBasketItemRepository extends JpaRepository<BasketItem,Integer> {
    List<BasketItem> findByBasketId(int id);
    @Query("select new com.etiya.ecommercedemopair7.business.response.basketItems.GetAllBasketItemResponse " +
            "(bi.id,bi.basket.id,bi.sellerProduct.id,bi.quantity,bi.itemTotalPrice) " +
            "from BasketItem bi")
    Slice<GetAllBasketItemResponse> findAllBasketItemsWithSlice(Pageable pageable);
}
