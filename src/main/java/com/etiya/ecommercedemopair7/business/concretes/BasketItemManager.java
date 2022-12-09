package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IBasketItemService;
import com.etiya.ecommercedemopair7.business.abstracts.IBasketService;
import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.business.request.basketItems.AddBasketItemRequest;
import com.etiya.ecommercedemopair7.business.response.basketItems.AddBasketItemResponse;
import com.etiya.ecommercedemopair7.business.response.basketItems.GetAllBasketItemResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Basket;
import com.etiya.ecommercedemopair7.entities.concretes.BasketItem;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import com.etiya.ecommercedemopair7.repository.abstracts.IBasketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketItemManager implements IBasketItemService {
    private IBasketItemRepository basketItemRepository;
    private IBasketService basketService;
    private IProductService productService;
    private IModelMapperService mapper;

    @Autowired
    public BasketItemManager(IBasketItemRepository basketItemRepository, IBasketService basketService, IProductService productService, IModelMapperService mapper) {
        this.basketItemRepository = basketItemRepository;
        this.basketService = basketService;
        this.productService = productService;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllBasketItemResponse> getAll() {
        List<BasketItem> basketItems = basketItemRepository.findAll();
        List<GetAllBasketItemResponse> response = basketItems.stream()
                .map(basketItem -> mapper.forResponse().map(basketItem, GetAllBasketItemResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public AddBasketItemResponse add(AddBasketItemRequest addBasketItemRequest) {

        getProduct(addBasketItemRequest);
        getBasket(addBasketItemRequest);

        BasketItem basketItem = mapper.forRequest().map(addBasketItemRequest, BasketItem.class);

        BasketItem savedBasketItem = basketItemRepository.save(basketItem);

        AddBasketItemResponse response = mapper.forResponse().map(savedBasketItem, AddBasketItemResponse.class);

        return response;
    }

    private Product getProduct(AddBasketItemRequest addBasketItemRequest) {
        Product product = productService.getByProductId(addBasketItemRequest.getProductId());
        return product;
    }

    private Basket getBasket(AddBasketItemRequest addBasketItemRequest) {
        Basket basket = basketService.getById(addBasketItemRequest.getBasketId());
        return basket;
    }
}
