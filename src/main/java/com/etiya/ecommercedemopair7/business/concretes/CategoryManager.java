package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ICategoryService;
import com.etiya.ecommercedemopair7.business.request.categories.AddCategoryRequest;
import com.etiya.ecommercedemopair7.business.response.categories.AddCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetAllCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetCategoryResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Category;
import com.etiya.ecommercedemopair7.repository.abstracts.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements ICategoryService {
    private ICategoryRepository categoryRepository;
    private IModelMapperService mapper;

    @Autowired
    CategoryManager(ICategoryRepository categoryRepository, IModelMapperService mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllCategoryResponse> getAll() {
        List<Category> categories = this.categoryRepository.findAll();
        List<GetAllCategoryResponse> response = categories.stream()
                .map(category -> this.mapper.forResponse().map(category, GetAllCategoryResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GetCategoryResponse getById(int categoryId) {
        Category category = existsByCategoryId(categoryId);
        GetCategoryResponse response = mapper.forResponse().map(category, GetCategoryResponse.class);
        return response;
    }

    @Override
    public Category getByCategoryId(int categoryId) {
        return existsByCategoryId(categoryId);
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category customGetByName(String name) {
        return categoryRepository.customFindByName(name);
    }

    @Override
    public AddCategoryResponse add(AddCategoryRequest addCategoryRequest) {
        //MANUAL MAPPING
        //Category category = new Category();
        //category.setName(addCategoryRequest.getName());
        //category.setRefId(addCategoryRequest.getRefId());

        //MODEL MAPPER
        Category category = mapper.forRequest().map(addCategoryRequest, Category.class);

        categoryCanNotExistWithSameName(addCategoryRequest.getName());

        Category savedCategory = categoryRepository.save(category);

        AddCategoryResponse response = mapper.forResponse().map(savedCategory, AddCategoryResponse.class);
        return response;
    }

    private void categoryCanNotExistWithSameName(String name) {
        boolean isExists = categoryRepository.existsCategoryByName(name);
        if (isExists)
            throw new RuntimeException("Bu isimle bir kategori zaten mevcut!");
    }

    private Category existsByCategoryId(int id) {
        Category currentCategory;
        try {
            currentCategory = this.categoryRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("İlgili kategori bulunamadı.");
        }
        return currentCategory;
    }
}
