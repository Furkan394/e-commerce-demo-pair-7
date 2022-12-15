package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.categories.AddCategoryRequest;
import com.etiya.ecommercedemopair7.business.response.categories.AddCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetAllCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetCategoryResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ICategoryService {
    DataResult<List<GetAllCategoryResponse>> getAll();
    DataResult<GetCategoryResponse> getById(int categoryId);
    DataResult<Category> getByCategoryId(int categoryId);
    DataResult<Category> getByName(String name);
    DataResult<Category> customGetByName(String name);
    DataResult<AddCategoryResponse> add(AddCategoryRequest addCategoryRequest);
    DataResult<Slice<GetAllCategoryResponse>> getAllCategoriesWithSlice(Pageable pageable);
}
