package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.request.categories.AddCategoryRequest;
import com.etiya.ecommercedemopair7.business.response.categories.AddCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetAllCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetCategoryResponse;
import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.mapping.ModelMapperManager;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.messages.MessageSourceManager;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Category;
import com.etiya.ecommercedemopair7.repository.abstracts.ICategoryRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryManagerTest {

    CategoryManager categoryManager;

    //@Mock
    ICategoryRepository categoryRepository;
    IModelMapperService modelMapperService;
    IMessageSourceService messageSourceService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(ICategoryRepository.class);
        modelMapperService = new ModelMapperManager(new ModelMapper());
        messageSourceService = new MessageSourceManager(getResourceBundle());

        categoryManager = new CategoryManager(categoryRepository, modelMapperService, messageSourceService);
    }

    ResourceBundleMessageSource getResourceBundle(){
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

    @Test
    void getAll() {
        // repository'deki findAll metodunu doldur
        List<Category> categoriesToReturn = new ArrayList<>();
        categoriesToReturn.add(Category.builder().id(1).refId(1).name("Kategori 1").build());

        when(categoryRepository.findAll()).thenReturn(categoriesToReturn); //getAll metodu için findAll ile 1 adet kategori listesi gelecek
        DataResult<List<GetAllCategoryResponse>> actualResult = categoryManager.getAll();

        assert categoriesToReturn.size() == actualResult.getData().size();
    }

    @Test
    void getById() { //TODO: düzelt

        Category categoryToReturn = Category.builder().id(1).refId(1).name("Kategori 1").build();
        Optional<Category> category = Optional.of(categoryToReturn);

        when(categoryRepository.findById(1)).thenReturn(category);

        DataResult<GetCategoryResponse> actualCategory = categoryManager.getById(1);

        assert actualCategory.getData().equals(categoryToReturn);
    }

    @Test
    void getByIdIfNotExistShouldThrowException(){
        assertThrows(BusinessException.class, () -> {
            categoryManager.getById(2);
        });
    }

    @Test
    void add() {
        when(categoryRepository.existsCategoryByName(any())).thenReturn(false); //parametre ne gelirse gelsin false
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest(1,"Kategori 1");
        Category categoryToAdd = Category.builder().refId(1).name("Kategori 1").build();
        when(categoryRepository.save(any())).thenReturn(categoryToAdd);

        DataResult<AddCategoryResponse> response = categoryManager.add(addCategoryRequest);

        assert response.getData().getName() == categoryToAdd.getName();
    }

    @Test
    void addCategoryIfWithSameNameShouldThrowBusinessException(){
        when(categoryRepository.existsCategoryByName(any())).thenReturn(true);
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest(1,"Kategori 1");

        assertThrows(BusinessException.class, () -> {
           categoryManager.add(addCategoryRequest);
        });
    }
}