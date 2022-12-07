package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.business.request.products.AddProductRequest;
import com.etiya.ecommercedemopair7.business.response.products.AddProductResponse;
import com.etiya.ecommercedemopair7.business.response.products.GetAllProductResponse;
import com.etiya.ecommercedemopair7.business.response.products.GetProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import com.etiya.ecommercedemopair7.repository.abstracts.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements IProductService {

    private IProductRepository productRepository;
    private IModelMapperService mapper;

    @Autowired
    ProductManager(IProductRepository productRepository, IModelMapperService mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getById(int productId) {
        return existsByProductId(productId);
    }

    @Override
    public Product getByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product customGetByName(String name) {
        return productRepository.customFindByName(name);
    }

    @Override
    public AddProductResponse add(AddProductRequest addProductRequest) {

        Product product = mapper.forRequest().map(addProductRequest, Product.class);

        Product savedProduct = productRepository.save(product);

        AddProductResponse response = mapper.forResponse().map(savedProduct, AddProductResponse.class);

        return response;
    }

    private Product existsByProductId(int id) {
        Product currentProduct;
        try {
            currentProduct = this.productRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("İlgili ürün bulunamadı.");
        }
        return currentProduct;
    }
}
