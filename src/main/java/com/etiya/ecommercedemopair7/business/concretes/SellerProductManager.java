package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.business.abstracts.ISellerProductService;
import com.etiya.ecommercedemopair7.business.abstracts.ISellerService;
import com.etiya.ecommercedemopair7.business.request.sellerProducts.AddSellerProductRequest;
import com.etiya.ecommercedemopair7.business.response.sellerProducts.AddSellerProductResponse;
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

    @Autowired
    public SellerProductManager(ISellerProductRepository sellerProductRepository, ISellerService sellerService, IProductService productService) {
        this.sellerProductRepository = sellerProductRepository;
        this.sellerService = sellerService;
        this.productService = productService;
    }

    @Override
    public AddSellerProductResponse add(AddSellerProductRequest addSellerProductRequest) {
        SellerProduct sellerProduct = new SellerProduct();

        Seller seller = getSeller(addSellerProductRequest);
        sellerProduct.setSeller(seller);

        Product product = getProduct(addSellerProductRequest);
        sellerProduct.setProduct(product);

        sellerProduct.setDescription(addSellerProductRequest.getDescription());
        sellerProduct.setImageUrl(addSellerProductRequest.getImageUrl());
        sellerProduct.setStock(addSellerProductRequest.getStock());
        sellerProduct.setUnitPrice(addSellerProductRequest.getUnitPrice());

        SellerProduct savedSellerProduct = sellerProductRepository.save(sellerProduct);

        return new AddSellerProductResponse(savedSellerProduct.getId(), savedSellerProduct.getSeller().getId(),
                savedSellerProduct.getProduct().getId(), savedSellerProduct.getDescription(), savedSellerProduct.getImageUrl(),
                savedSellerProduct.getStock(), savedSellerProduct.getUnitPrice());
    }

    private Product getProduct(AddSellerProductRequest addSellerProductRequest) {
        Product product = productService.getById(addSellerProductRequest.getProductId());
        return product;
    }

    private Seller getSeller(AddSellerProductRequest addSellerProductRequest) {
        Seller seller = sellerService.getById(addSellerProductRequest.getSellerId());
        return seller;
    }
}
