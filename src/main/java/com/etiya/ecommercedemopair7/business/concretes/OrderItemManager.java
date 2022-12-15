package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.*;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.orderItems.AddOrderItemRequest;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.Result;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessResult;
import com.etiya.ecommercedemopair7.entities.concretes.Basket;
import com.etiya.ecommercedemopair7.entities.concretes.BasketItem;
import com.etiya.ecommercedemopair7.entities.concretes.OrderItem;
import com.etiya.ecommercedemopair7.entities.dtos.OrderItemDto;
import com.etiya.ecommercedemopair7.repository.abstracts.IOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemManager implements IOrderItemService {

    private IOrderItemRepository orderItemRepository;
    private IModelMapperService mapper;
    private IMessageSourceService messageSourceService;
    private IBasketService basketService;
    private IBasketItemService basketItemService;

    @Autowired
    public OrderItemManager(IOrderItemRepository orderItemRepository, IModelMapperService mapper,
                            IMessageSourceService messageSourceService, IBasketService basketService,
                            IBasketItemService basketItemService) {
        this.orderItemRepository = orderItemRepository;
        this.mapper = mapper;
        this.messageSourceService = messageSourceService;
        this.basketService = basketService;
        this.basketItemService = basketItemService;
    }

    @Override
    public DataResult<List<OrderItemDto>> getOrderItemDto() {
        //TODO: null değerler var
        List<OrderItem> orderItems = orderItemRepository.findAll();
        List<OrderItemDto> response = orderItems.stream()
                .map(orderItem -> mapper.forResponse().map(orderItem, OrderItemDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.OrderItem.orderItemsListed));
    }

    @Transactional
    @Override
    public Result add(AddOrderItemRequest addOrderItemRequest) {

        Basket basket = basketService.getByCustomerId(addOrderItemRequest.getCustomerId());
        List<BasketItem> basketItems = basketItemService.getByBasketItemId(basket.getId());

        saveOrderItem(addOrderItemRequest, basketItems);

        return new SuccessResult(messageSourceService.getMessage(Messages.OrderItem.orderItemAdded));
    }

    private void saveOrderItem(AddOrderItemRequest addOrderItemRequest, List<BasketItem> basketItems) {
        for (BasketItem basketItem : basketItems) {
            addOrderItemRequest.setItemTotalPrice(basketItem.getItemTotalPrice());
            addOrderItemRequest.setQuantity(basketItem.getQuantity());
            addOrderItemRequest.setSellerProductId(basketItem.getSellerProduct().getId());
            OrderItem orderItem = mapper.forRequest().map(addOrderItemRequest, OrderItem.class);
            orderItemRepository.save(orderItem);
        }
    }
}
