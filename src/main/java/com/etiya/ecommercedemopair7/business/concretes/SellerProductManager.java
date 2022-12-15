package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.business.abstracts.ISellerProductService;
import com.etiya.ecommercedemopair7.business.abstracts.ISellerService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.sellerProducts.AddSellerProductRequest;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.AddSellerProductResponse;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetAllSellerProductResponse;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.GetSellerProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import com.etiya.ecommercedemopair7.entities.concretes.Seller;
import com.etiya.ecommercedemopair7.entities.concretes.SellerProduct;
import com.etiya.ecommercedemopair7.repository.abstracts.ISellerProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerProductManager implements ISellerProductService {

    private ISellerProductRepository sellerProductRepository;
    private ISellerService sellerService;
    private IProductService productService;
    private IModelMapperService mapper;
    private IMessageSourceService messageSourceService;

    @Autowired
    public SellerProductManager(ISellerProductRepository sellerProductRepository, ISellerService sellerService,
                                IProductService productService, IModelMapperService mapper,
                                IMessageSourceService messageSourceService) {
        this.sellerProductRepository = sellerProductRepository;
        this.sellerService = sellerService;
        this.productService = productService;
        this.mapper = mapper;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public DataResult<List<GetAllSellerProductResponse>> getAll() {
        List<SellerProduct> sellerProducts = sellerProductRepository.findAll();
        List<GetAllSellerProductResponse> response = sellerProducts.stream()
                .map(sellerProduct -> mapper.forResponse().map(sellerProduct, GetAllSellerProductResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Product.productsListed));
    }

    @Override
    public DataResult<AddSellerProductResponse> add(AddSellerProductRequest addSellerProductRequest) {

        getSeller(addSellerProductRequest);
        getProduct(addSellerProductRequest);

        SellerProduct sellerProduct = mapper.forRequest().map(addSellerProductRequest, SellerProduct.class);

        SellerProduct savedSellerProduct = sellerProductRepository.save(sellerProduct);

        AddSellerProductResponse response = mapper.forResponse().map(savedSellerProduct, AddSellerProductResponse.class);

        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Product.productAdded));
    }

    @Override
    public DataResult<GetSellerProductResponse> getById(int id) {
        SellerProduct sellerProduct = checkIfSellerProductExistsById(id);
        GetSellerProductResponse response = mapper.forResponse().map(sellerProduct, GetSellerProductResponse.class);
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Product.productReceived));
    }

    @Override
    public SellerProduct getBySellerProductId(int id) {
        return checkIfSellerProductExistsById(id);
    }

    private DataResult<Product> getProduct(AddSellerProductRequest addSellerProductRequest) {
        DataResult<Product> product = productService.getByProductId(addSellerProductRequest.getProductId());
        return product;
    }
    @Override
    public DataResult<Page<GetAllSellerProductResponse>> getSellerProductsWithPage(Pageable pageable) {
        return new SuccessDataResult<>(sellerProductRepository.findAllSellerProductsWithPage(pageable),
                messageSourceService.getMessage(Messages.Product.productsListed));
    }

    private DataResult<Seller> getSeller(AddSellerProductRequest addSellerProductRequest) {
        DataResult<Seller> seller = sellerService.getBySellerId(addSellerProductRequest.getSellerId());
        return seller;
    }

    private SellerProduct checkIfSellerProductExistsById(int id) {
        SellerProduct currentSellerProduct;
        try {
            currentSellerProduct = this.sellerProductRepository.findById(id).get();
        } catch (Exception e) {
            throw new BusinessException(messageSourceService.getMessage(Messages.Product.productNotFound));
        }
        return currentSellerProduct;
    }
}