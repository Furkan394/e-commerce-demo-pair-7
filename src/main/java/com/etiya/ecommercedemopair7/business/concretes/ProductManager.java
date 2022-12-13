package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.products.AddProductRequest;
import com.etiya.ecommercedemopair7.business.response.products.AddProductResponse;
import com.etiya.ecommercedemopair7.business.response.products.GetAllProductResponse;
import com.etiya.ecommercedemopair7.business.response.products.GetProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import com.etiya.ecommercedemopair7.repository.abstracts.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductManager implements IProductService {

    private IProductRepository productRepository;
    private IModelMapperService mapper;
    private MessageSource messageSource;

    @Autowired
    ProductManager(IProductRepository productRepository, IModelMapperService mapper, MessageSource messageSource) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.messageSource = messageSource;
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

    @Override
    public DataResult<Page<GetAllProductResponse>> getAllWithPagination(Pageable pageable) {
        return new SuccessDataResult<>(productRepository.findAllProducts(pageable), Messages.Product.productsListed);
    }

    @Override
    public DataResult<Slice<Product>> getAllWithSlice(Pageable pageable) {
        return new SuccessDataResult<>(productRepository.findAllWithSlice(pageable), Messages.Product.productsListed);
    }

    private Product existsByProductId(int id) {
        Product currentProduct;
        try {
            currentProduct = this.productRepository.findById(id).get();
        } catch (Exception e) {
            throw new BusinessException(messageSource.getMessage(Messages.Product.productNotFound,
                    null, LocaleContextHolder.getLocale()));
        }
        return currentProduct;
    }
}
