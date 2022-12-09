package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ICategoryService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.categories.AddCategoryRequest;
import com.etiya.ecommercedemopair7.business.response.categories.AddCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetAllCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetCategoryResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
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
    public DataResult<List<GetAllCategoryResponse>> getAll() {
        List<Category> categories = this.categoryRepository.findAll();
        List<GetAllCategoryResponse> response = categories.stream()
                .map(category -> this.mapper.forResponse().map(category, GetAllCategoryResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, Messages.Category.categoriesListed);
    }

    @Override
    public DataResult<GetCategoryResponse> getById(int categoryId) {
        Category category = existsByCategoryId(categoryId);
        GetCategoryResponse response = mapper.forResponse().map(category, GetCategoryResponse.class);
        return new SuccessDataResult<>(response, Messages.Category.categoryReceived);
    }

    @Override
    public DataResult<Category> getByCategoryId(int categoryId) {
        return new SuccessDataResult<>(existsByCategoryId(categoryId), Messages.Category.categoryReceived);
    }

    @Override
    public DataResult<Category> getByName(String name) {
        return new SuccessDataResult<>(categoryRepository.findByName(name), Messages.Category.categoryReceived);
    }

    @Override
    public DataResult<Category> customGetByName(String name) {
        return new SuccessDataResult<>(categoryRepository.customFindByName(name), Messages.Category.categoryReceived);
    }

    @Override
    public DataResult<AddCategoryResponse> add(AddCategoryRequest addCategoryRequest) {
        //MANUAL MAPPING
        //Category category = new Category();
        //category.setName(addCategoryRequest.getName());
        //category.setRefId(addCategoryRequest.getRefId());

        //MODEL MAPPER
        Category category = mapper.forRequest().map(addCategoryRequest, Category.class);

        categoryCanNotExistWithSameName(addCategoryRequest.getName());

        Category savedCategory = categoryRepository.save(category);

        AddCategoryResponse response = mapper.forResponse().map(savedCategory, AddCategoryResponse.class);
        return new SuccessDataResult<>(response, Messages.Category.categoryAdded);
    }

    private void categoryCanNotExistWithSameName(String name) {
        boolean isExists = categoryRepository.existsCategoryByName(name);
        if (isExists)
            throw new RuntimeException(Messages.Category.categoryExistsWithSameName);
    }

    private Category existsByCategoryId(int id) {
        Category currentCategory;
        try {
            currentCategory = this.categoryRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.Category.categoryNotFound);
        }
        return currentCategory;
    }
}
