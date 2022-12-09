package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.ISellerService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.sellers.AddSellerRequest;
import com.etiya.ecommercedemopair7.business.response.sellers.AddSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetAllSellerResponse;
import com.etiya.ecommercedemopair7.business.response.sellers.GetSellerResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
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
    public DataResult<List<GetAllSellerResponse>> getAll() {
        List<Seller> sellers = sellerRepository.findAll();
        List<GetAllSellerResponse> response = sellers.stream()
                .map(seller -> mapper.forResponse().map(seller, GetAllSellerResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, Messages.Seller.sellersListed);
    }

    @Override
    public DataResult<GetSellerResponse> getById(int sellerId) {
        Seller seller = existsBySellerId(sellerId);
        GetSellerResponse response = mapper.forResponse().map(seller, GetSellerResponse.class);
        return new SuccessDataResult<>(response, Messages.Seller.sellerReceived);
    }

    @Override
    public DataResult<Seller> getBySellerId(int sellerId) {
        return new SuccessDataResult<>(existsBySellerId(sellerId), Messages.Seller.sellerReceived);
    }

    @Override
    public DataResult<AddSellerResponse> add(AddSellerRequest addSellerRequest) {

        Seller seller = mapper.forRequest().map(addSellerRequest, Seller.class);

        Seller savedSeller = sellerRepository.save(seller);

        AddSellerResponse response = mapper.forResponse().map(savedSeller, AddSellerResponse.class);

        return new SuccessDataResult<>(response, Messages.Seller.sellerAdded);
    }

    private Seller existsBySellerId(int sellerId) {
        Seller currentSeller;
        try {
            currentSeller = this.sellerRepository.findById(sellerId).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.Seller.sellerNotFound);
        }
        return currentSeller;
    }
}
