package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.productCharValues.GetAllProductCharValueResponse;
import com.etiya.ecommercedemopair7.entities.concretes.ProductCharValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductCharValueRepository extends JpaRepository<ProductCharValue,Integer> {
    @Query("Select new com.etiya.ecommercedemopair7.business.response.productCharValues.GetAllProductCharValueResponse "+
            "(pr.id,pr.name,pr.productChar.id) From ProductCharValue pr")
    Page<GetAllProductCharValueResponse> findAllProductCharValuesWithPage(Pageable pageable);
}
