package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IAddressService;
import com.etiya.ecommercedemopair7.business.abstracts.IDeliveryOptionService;
import com.etiya.ecommercedemopair7.business.abstracts.IOrderService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.orders.AddOrderRequest;
import com.etiya.ecommercedemopair7.business.response.orders.AddOrderResponse;
import com.etiya.ecommercedemopair7.business.response.orders.GetAllOrderResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Address;
import com.etiya.ecommercedemopair7.entities.concretes.DeliveryOption;
import com.etiya.ecommercedemopair7.entities.concretes.Order;
import com.etiya.ecommercedemopair7.entities.dtos.OrderDto;
import com.etiya.ecommercedemopair7.repository.abstracts.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManager implements IOrderService {
    private IOrderRepository orderRepository;
    private IDeliveryOptionService deliveryOptionService;
    private IAddressService addressService;
    private IModelMapperService mapper;

    @Autowired
    public OrderManager(IOrderRepository orderRepository, IDeliveryOptionService deliveryOptionService, IAddressService addressService, IModelMapperService mapper) {
        this.orderRepository = orderRepository;
        this.deliveryOptionService = deliveryOptionService;
        this.addressService = addressService;
        this.mapper = mapper;
    }

    @Override
    public DataResult<List<GetAllOrderResponse>> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<GetAllOrderResponse> response = orders.stream()
                .map(order -> mapper.forResponse().map(order, GetAllOrderResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, Messages.Order.ordersListed);
    }

    @Override
    public DataResult<AddOrderResponse> add(AddOrderRequest addOrderRequest) {

        getDeliveryOption(addOrderRequest);
        getOrderAddress(addOrderRequest.getOrderAddressId());
        getInvoiceAddress(addOrderRequest.getInvoiceAddressId());

        Order order = mapper.forRequest().map(addOrderRequest, Order.class);

        Order savedOrder = orderRepository.save(order);

        AddOrderResponse response = mapper.forResponse().map(savedOrder, AddOrderResponse.class);

        return new SuccessDataResult<>(response, Messages.Order.orderAdded);
    }

    @Override
    public DataResult<List<OrderDto>> getOrderDto() {
        //TODO: İç içe dtolar hepsi maplenecek
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> response = orders.stream().map(order -> mapper.forResponse().map(order, OrderDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response, Messages.Order.ordersListed);
    }

    private DataResult<Address> getInvoiceAddress(int invoiceAddressId) {
        DataResult<Address> invoiceAddress = addressService.getByAddressId(invoiceAddressId);
        return invoiceAddress;
    }

    private DataResult<Address> getOrderAddress(int orderAddressId) {
        DataResult<Address> orderAddress = addressService.getByAddressId(orderAddressId);
        return orderAddress;
    }

    private DataResult<DeliveryOption> getDeliveryOption(AddOrderRequest addOrderRequest) {
        DataResult<DeliveryOption> deliveryOption = deliveryOptionService.getByDeliveryOptionId(addOrderRequest.getDeliveryOptionId());
        return deliveryOption;
    }
}
