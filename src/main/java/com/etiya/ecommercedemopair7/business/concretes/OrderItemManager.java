package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IOrderItemService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.OrderItem;
import com.etiya.ecommercedemopair7.entities.dtos.OrderItemDto;
import com.etiya.ecommercedemopair7.repository.abstracts.IOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemManager implements IOrderItemService {

    private IOrderItemRepository orderItemRepository;
    private IModelMapperService mapper;

    @Autowired
    public OrderItemManager(IOrderItemRepository orderItemRepository, IModelMapperService mapper) {
        this.orderItemRepository = orderItemRepository;
        this.mapper = mapper;
    }

    @Override
    public DataResult<List<OrderItemDto>> getOrderItemDto() {
        //TODO: null değerler var
        List<OrderItem> orderItems = orderItemRepository.findAll();
        List<OrderItemDto> response = orderItems.stream().map(orderItem -> mapper.forResponse().map(orderItem, OrderItemDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response, Messages.Order.ordersListed);
    }
}
