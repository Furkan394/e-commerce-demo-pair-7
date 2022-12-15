package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.productChars.GetAllProductCharResponse;
import com.etiya.ecommercedemopair7.entities.concretes.ProductChar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductCharRepository extends JpaRepository<ProductChar,Integer> {
    @Query("Select new com.etiya.ecommercedemopair7.business.response.productChars.GetAllProductCharResponse "+
            "(pr.id,pr.name,pr.description) From ProductChar pr")
    Page<GetAllProductCharResponse> findAllProductCharsWithPage(Pageable pageable);
}
