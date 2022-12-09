package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ICategoryService;
import com.etiya.ecommercedemopair7.business.abstracts.IProductCategoryService;
import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.business.request.productCategories.AddProductCategoryRequest;
import com.etiya.ecommercedemopair7.business.response.productCategories.AddProductCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.productCategories.GetAllProductCategoryResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Category;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import com.etiya.ecommercedemopair7.entities.concretes.ProductCategory;
import com.etiya.ecommercedemopair7.repository.abstracts.IProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryManager implements IProductCategoryService {

    private IProductCategoryRepository productCategoryRepository;
    private ICategoryService categoryService;
    private IProductService productService;
    private IModelMapperService mapper;

    @Autowired
    public ProductCategoryManager(IProductCategoryRepository productCategoryRepository,
                                  ICategoryService categoryService, IProductService productService, IModelMapperService mapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productService = productService;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllProductCategoryResponse> getAll() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        List<GetAllProductCategoryResponse> response = productCategories.stream()
                .map(productCategory -> mapper.forResponse().map(productCategory, GetAllProductCategoryResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public ProductCategory getByCategoryId(int categoryId) {
        return this.productCategoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public ProductCategory getByProductId(int productId) {
        return this.productCategoryRepository.findByProductId(productId);
    }

    @Override
    public AddProductCategoryResponse add(AddProductCategoryRequest addProductCategoryRequest) {

        existsByProduct(addProductCategoryRequest);
        existsByCategory(addProductCategoryRequest);

        ProductCategory productCategory = mapper.forRequest().map(addProductCategoryRequest, ProductCategory.class);

        ProductCategory savedProductCategory = productCategoryRepository.save(productCategory);

        AddProductCategoryResponse response = mapper.forResponse().map(savedProductCategory, AddProductCategoryResponse.class);

        return response;
    }

    private Product existsByProduct(AddProductCategoryRequest addProductCategoryRequest) {
        Product product = productService.getByProductId(addProductCategoryRequest.getProductId());
        return product;
    }

    private Category existsByCategory(AddProductCategoryRequest addProductCategoryRequest) {
        Category category = categoryService.getByCategoryId(addProductCategoryRequest.getCategoryId());
        return category;
    }
}
