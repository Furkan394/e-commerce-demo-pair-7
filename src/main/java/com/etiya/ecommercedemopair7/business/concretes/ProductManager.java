package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.products.AddProductRequest;
import com.etiya.ecommercedemopair7.business.response.products.AddProductResponse;
import com.etiya.ecommercedemopair7.business.response.products.GetAllProductResponse;
import com.etiya.ecommercedemopair7.business.response.products.GetProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import com.etiya.ecommercedemopair7.repository.abstracts.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public DataResult<List<GetAllProductResponse>> getAll() {

        List<Product> products = this.productRepository.findAll();
        List<GetAllProductResponse> response = products.stream()
                .map(product -> this.mapper.forResponse().map(product, GetAllProductResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, Messages.Product.productsListed);
    }

    @Override
    public DataResult<GetProductResponse> getById(int productId) {
        Product product = existsByProductId(productId);
        GetProductResponse response = mapper.forResponse().map(product, GetProductResponse.class);
        return new SuccessDataResult<>(response, Messages.Product.productReceived);
    }

    @Override
    public DataResult<Product> getByProductId(int productId) {
        return new SuccessDataResult<>(existsByProductId(productId), Messages.Product.productReceived);
    }

    @Override
    public DataResult<Product> getByName(String name) {
        return new SuccessDataResult<>(productRepository.findByName(name), Messages.Product.productReceived);
    }

    @Override
    public DataResult<Product> customGetByName(String name) {
        return new SuccessDataResult<>(productRepository.customFindByName(name), Messages.Product.productReceived);
    }

    @Override
    public DataResult<AddProductResponse> add(AddProductRequest addProductRequest) {

        Product product = mapper.forRequest().map(addProductRequest, Product.class);

        Product savedProduct = productRepository.save(product);

        AddProductResponse response = mapper.forResponse().map(savedProduct, AddProductResponse.class);

        return new SuccessDataResult<>(response, Messages.Product.productAdded);
    }

    private Product existsByProductId(int id) {
        Product currentProduct;
        try {
            currentProduct = this.productRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.Product.productNotFound);
        }
        return currentProduct;
    }
}
