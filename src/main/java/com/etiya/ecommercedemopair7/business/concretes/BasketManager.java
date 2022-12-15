package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IBasketService;
import com.etiya.ecommercedemopair7.business.abstracts.ICustomerService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.baskets.AddBasketRequest;
import com.etiya.ecommercedemopair7.business.request.baskets.UpdateBasketRequest;
import com.etiya.ecommercedemopair7.business.response.baskets.AddBasketResponse;
import com.etiya.ecommercedemopair7.business.response.baskets.GetAllBasketResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Basket;
import com.etiya.ecommercedemopair7.repository.abstracts.IBasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketManager implements IBasketService {
    private IBasketRepository basketRepository;
    private ICustomerService customerService;
    private IModelMapperService mapper;
    private IMessageSourceService messageSourceService;

    @Autowired
    public BasketManager(IBasketRepository basketRepository, ICustomerService customerService, IModelMapperService mapper,
                         IMessageSourceService messageSourceService) {
        this.basketRepository = basketRepository;
        this.customerService = customerService;
        this.mapper = mapper;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public DataResult<List<GetAllBasketResponse>> getAll() {
        List<Basket> baskets = basketRepository.findAll();
        List<GetAllBasketResponse> response = baskets.stream()
                .map(basket -> mapper.forResponse().map(basket, GetAllBasketResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Basket.basketsListed));
    }

    @Override
    public Basket createBasket(AddBasketRequest addBasketRequest) {
        Basket basket = mapper.forRequest().map(addBasketRequest, Basket.class);
        Basket savedBasket = basketRepository.save(basket);
        return savedBasket;
    }

    @Override
    public DataResult<AddBasketResponse> updateBasket(UpdateBasketRequest updateBasketRequest, Basket getBasket) {
        Basket basket = mapper.forRequest().map(updateBasketRequest, Basket.class);
        basket.setId(getBasket.getId());
        basket.setShippingPrice(getBasket.getShippingPrice());
        basket.setTotalPrice(getBasket.getTotalPrice());

        Basket savedBasket = basketRepository.save(basket);

        AddBasketResponse response = mapper.forResponse().map(savedBasket, AddBasketResponse.class);
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Basket.basketAdded));
    }

    @Override
    public Basket getByCustomerId(int customerId) {
        return basketRepository.findByCustomerId(customerId);
    }

    @Override
    public DataResult<Page<GetAllBasketResponse>> getAllBasketsWithPage(Pageable pageable) {
        return new SuccessDataResult<>(basketRepository.findAllBasketsWithPage(pageable),
                messageSourceService.getMessage(Messages.Basket.basketsListed));
    }


}

