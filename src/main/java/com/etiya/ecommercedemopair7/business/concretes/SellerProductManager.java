package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.business.abstracts.ISellerProductService;
import com.etiya.ecommercedemopair7.business.abstracts.ISellerService;
import com.etiya.ecommercedemopair7.business.request.sellerProducts.AddSellerProductRequest;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.AddSellerProductResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import com.etiya.ecommercedemopair7.entities.concretes.Seller;
import com.etiya.ecommercedemopair7.entities.concretes.SellerProduct;
import com.etiya.ecommercedemopair7.repository.abstracts.ISellerProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerProductManager implements ISellerProductService {

    private ISellerProductRepository sellerProductRepository;
    private ISellerService sellerService;
    private IProductService productService;
    private IModelMapperService mapper;

    @Autowired
    public SellerProductManager(ISellerProductRepository sellerProductRepository, ISellerService sellerService,
                                IProductService productService, IModelMapperService mapper) {
        this.sellerProductRepository = sellerProductRepository;
        this.sellerService = sellerService;
        this.productService = productService;
        this.mapper = mapper;
    }

    @Override
    public AddSellerProductResponse add(AddSellerProductRequest addSellerProductRequest) {

        getSeller(addSellerProductRequest);
        getProduct(addSellerProductRequest);

        SellerProduct sellerProduct = mapper.forRequest().map(addSellerProductRequest, SellerProduct.class);

        SellerProduct savedSellerProduct = sellerProductRepository.save(sellerProduct);

        AddSellerProductResponse response = mapper.forResponse().map(savedSellerProduct, AddSellerProductResponse.class);

        return response;
    }

    private Product getProduct(AddSellerProductRequest addSellerProductRequest) {
        Product product = productService.getByProductId(addSellerProductRequest.getProductId());
        return product;
    }

    private Seller getSeller(AddSellerProductRequest addSellerProductRequest) {
        Seller seller = sellerService.getBySellerId(addSellerProductRequest.getSellerId());
        return seller;
    }
}
