package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.categories.GetAllCategoryResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);

    boolean existsCategoryByName(String name);
    @Query("SELECT p from Category as p where p.name=:name")
    Category customFindByName(String name);

    @Query("select new com.etiya.ecommercedemopair7.business.response.categories.GetAllCategoryResponse " +
            "(c.id,c.refId,c.name)" +
            "from Category c")
    Slice<GetAllCategoryResponse> findAllCategoriesWithSlice(Pageable pageable);
}
