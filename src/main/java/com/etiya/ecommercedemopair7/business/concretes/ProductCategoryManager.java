package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductCategoryService;
import com.etiya.ecommercedemopair7.business.abstracts.concretes.ProductCategory;
import com.etiya.ecommercedemopair7.repository.abstracts.IProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryManager implements IProductCategoryService {

    private IProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryManager(IProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductCategory getByCategoryId(int categoryId) {
        return productCategoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public ProductCategory getByProductId(int productId) {
        return productCategoryRepository.findByProductId(productId);
    }
}
