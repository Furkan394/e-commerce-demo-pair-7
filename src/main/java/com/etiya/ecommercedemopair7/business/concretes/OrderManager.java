package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IDeliveryOptionService;
import com.etiya.ecommercedemopair7.business.abstracts.IOrderService;
import com.etiya.ecommercedemopair7.business.request.orders.AddOrderRequest;
import com.etiya.ecommercedemopair7.business.response.orders.AddOrderResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Address;
import com.etiya.ecommercedemopair7.entities.concretes.DeliveryOption;
import com.etiya.ecommercedemopair7.entities.concretes.Order;
import com.etiya.ecommercedemopair7.repository.abstracts.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManager implements IOrderService {
    private IOrderRepository orderRepository;
    private IDeliveryOptionService deliveryOptionService;

    @Autowired
    public OrderManager(IOrderRepository orderRepository, IDeliveryOptionService deliveryOptionService, IAddressService addressService, IModelMapperService mapper) {
        this.orderRepository = orderRepository;
        this.deliveryOptionService = deliveryOptionService;
        this.addressService = addressService;
        this.mapper = mapper;
    }

    @Override
    public AddOrderResponse add(AddOrderRequest addOrderRequest) {
        Order order = new Order();
        order.setOrderNumber(addOrderRequest.getOrderNumber());
        order.setTotalPrice(addOrderRequest.getTotalPrice());
        order.setOrderDate(addOrderRequest.getOrderDate());

        DeliveryOption deliveryOption = deliveryOptionService.getById(addOrderRequest.getDeliveryOptionId());
        order.setDeliveryOption(deliveryOption);

        Order savedOrder = orderRepository.save(order);

        AddOrderResponse response = mapper.forResponse().map(savedOrder, AddOrderResponse.class);

        return response;
    }
}
