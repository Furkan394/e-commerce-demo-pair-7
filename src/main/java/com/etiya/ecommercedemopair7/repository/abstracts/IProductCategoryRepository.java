package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.entities.concretes.ProductCategory;
import com.etiya.ecommercedemopair7.entities.dtos.ProductCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    ProductCategory findByCategoryId(int categoryId);
    ProductCategory findByProductId(int productId);

    @Query("Select new com.etiya.ecommercedemopair7.entities.dtos.ProductCategoryDto (p.id,pc.name, c.name,c.refId)" +
            " From ProductCategory p INNER JOIN p.product pc INNER JOIN p.category c")
    List<ProductCategoryDto> getProductCategoryDto();
}
