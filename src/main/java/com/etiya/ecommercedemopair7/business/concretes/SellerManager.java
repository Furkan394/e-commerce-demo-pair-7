package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ISellerService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.sellers.AddSellerRequest;
import com.etiya.ecommercedemopair7.business.response.sellers.AddSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetAllSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetSellerResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Seller;
import com.etiya.ecommercedemopair7.repository.abstracts.ISellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerManager implements ISellerService {

    private ISellerRepository sellerRepository;
    private IModelMapperService mapper;

    @Autowired
    public SellerManager(ISellerRepository sellerRepository, IModelMapperService mapper) {
        this.sellerRepository = sellerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllSellerResponse> getAll() {
        List<Seller> sellers = sellerRepository.findAll();
        List<GetAllSellerResponse> response = sellers.stream()
                .map(seller -> mapper.forResponse().map(seller, GetAllSellerResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GetSellerResponse getById(int sellerId) {
        Seller seller = existsBySellerId(sellerId);
        GetSellerResponse response = mapper.forResponse().map(seller, GetSellerResponse.class);
        return response;
    }

    @Override
    public Seller getBySellerId(int sellerId) {
        return existsBySellerId(sellerId);
    }

    @Override
    public AddSellerResponse add(AddSellerRequest addSellerRequest) {

        Seller seller = mapper.forRequest().map(addSellerRequest, Seller.class);

        Seller savedSeller = sellerRepository.save(seller);

        AddSellerResponse response = mapper.forResponse().map(savedSeller, AddSellerResponse.class);

        return response;
    }

    private Seller existsBySellerId(int sellerId) {
        Seller currentSeller;
        try {
            currentSeller = this.sellerRepository.findById(sellerId).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.Seller.SellerNotFound);
        }
        return currentSeller;
    }
}
