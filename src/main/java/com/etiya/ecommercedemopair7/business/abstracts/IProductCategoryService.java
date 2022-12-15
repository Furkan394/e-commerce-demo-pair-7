package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.productCategories.AddProductCategoryRequest;
import com.etiya.ecommercedemopair7.business.response.productCategories.AddProductCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.productCategories.GetAllProductCategoryResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.ProductCategory;
import com.etiya.ecommercedemopair7.entities.dtos.ProductCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductCategoryService {
    DataResult<List<GetAllProductCategoryResponse>> getAll();
    DataResult<ProductCategory> getByCategoryId(int categoryId);
    DataResult<ProductCategory> getByProductId(int productId);
    DataResult<AddProductCategoryResponse> add(AddProductCategoryRequest addProductCategoryRequest);
    DataResult<List<ProductCategoryDto>> getProductCategoryDto();
    DataResult<Page<GetAllProductCategoryResponse>> getAllProductCategoriesWithPage(Pageable pageable);
}
