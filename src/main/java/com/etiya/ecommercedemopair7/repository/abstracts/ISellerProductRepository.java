package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetAllSellerProductResponse;
import com.etiya.ecommercedemopair7.entities.concretes.SellerProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISellerProductRepository extends JpaRepository<SellerProduct, Integer> {
    @Query("Select new com.etiya.ecommercedemopair7.business.response.sellerProducts.GetAllSellerProductResponse "+
            "(sp.id, sp.seller.id,sp.product.id,sp.description,sp.imageUrl,sp.stock,sp.unitPrice) From SellerProduct sp")
    Page<GetAllSellerProductResponse> findAllSellerProductsWithPage(Pageable pageable);
}
