package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.*;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.invoices.AddInvoiceRequest;
import com.etiya.ecommercedemopair7.business.request.orderItems.AddOrderItemRequest;
import com.etiya.ecommercedemopair7.business.request.orders.AddOrderRequest;
import com.etiya.ecommercedemopair7.business.response.orders.AddOrderResponse;
import com.etiya.ecommercedemopair7.business.response.orders.GetAllOrderResponse;
import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Address;
import com.etiya.ecommercedemopair7.entities.concretes.DeliveryOption;
import com.etiya.ecommercedemopair7.entities.concretes.Invoice;
import com.etiya.ecommercedemopair7.entities.concretes.Order;
import com.etiya.ecommercedemopair7.entities.dtos.OrderDto;
import com.etiya.ecommercedemopair7.repository.abstracts.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderManager implements IOrderService {
    private IOrderRepository orderRepository;
    private IDeliveryOptionService deliveryOptionService;
    private IAddressService addressService;
    private IModelMapperService mapper;
    private IMessageSourceService messageSourceService;
    private IInvoiceService invoiceService;
    private IOrderItemService orderItemService;

    @Autowired
    public OrderManager(IOrderRepository orderRepository, IDeliveryOptionService deliveryOptionService,
                        IAddressService addressService, IModelMapperService mapper,
                        IMessageSourceService messageSourceService, IInvoiceService invoiceService,
                        IOrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.deliveryOptionService = deliveryOptionService;
        this.addressService = addressService;
        this.mapper = mapper;
        this.messageSourceService = messageSourceService;
        this.invoiceService = invoiceService;
        this.orderItemService = orderItemService;
    }

    @Override
    public DataResult<List<GetAllOrderResponse>> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<GetAllOrderResponse> response = orders.stream()
                .map(order -> mapper.forResponse().map(order, GetAllOrderResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Order.ordersListed));
    }

    @Transactional
    @Override
    public DataResult<AddOrderResponse> add(AddOrderRequest addOrderRequest) {

        getDeliveryOption(addOrderRequest);
        getOrderAddress(addOrderRequest.getOrderAddressId());
        getInvoiceAddress(addOrderRequest.getInvoiceAddressId());

        Order order = mapper.forRequest().map(addOrderRequest, Order.class);
        order.setOrderDate(LocalDate.now());
        order.setOrderNumber(String.valueOf(randomNumberGenerator()));

        Order savedOrder = orderRepository.save(order);

        AddOrderResponse response = mapper.forResponse().map(savedOrder, AddOrderResponse.class);

        addOrderItem(savedOrder);

        addInvoice(savedOrder);

        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Order.orderAdded));
    }

    private void addOrderItem(Order savedOrder) {
        AddOrderItemRequest addOrderItemRequest = new AddOrderItemRequest();
        addOrderItemRequest.setOrderId(savedOrder.getId());
        addOrderItemRequest.setCustomerId(savedOrder.getPayment().getCustomer().getId());

        orderItemService.add(addOrderItemRequest);
    }

    private void addInvoice(Order savedOrder) {
        Invoice invoice = new Invoice();
        invoice.setOrder(savedOrder);
        invoice.setCreatedDate(LocalDate.now());
        invoice.setNumber(String.valueOf(randomNumberGenerator()));

        AddInvoiceRequest request = mapper.forRequest().map(invoice, AddInvoiceRequest.class);

        invoiceService.add(request);
    }

    private static int randomNumberGenerator() {
        Random random = new Random();
        int invoiceNumber = random.nextInt(1000000) + 1;
        return invoiceNumber;
    }

    @Override
    public DataResult<List<OrderDto>> getOrderDto() {
        //TODO: İç içe dtolar hepsi maplenecek
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> response = orders.stream()
                .map(order -> mapper.forResponse().map(order, OrderDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Order.ordersListed));
    }

    @Override
    public Order getByOrderId(int orderId) {
        return checkIfOrderExistsById(orderId);
    }

    private Address getInvoiceAddress(int invoiceAddressId) {
        Address invoiceAddress = addressService.getByAddressId(invoiceAddressId);
        return invoiceAddress;
    }

    private Address getOrderAddress(int orderAddressId) {
        Address orderAddress = addressService.getByAddressId(orderAddressId);
        return orderAddress;
    }

    private DeliveryOption getDeliveryOption(AddOrderRequest addOrderRequest) {
        DeliveryOption deliveryOption = deliveryOptionService.getByDeliveryOptionId(addOrderRequest.getDeliveryOptionId());
        return deliveryOption;
    }

    private Order checkIfOrderExistsById(int orderId) {
        Order currentOrder;
        try {
            currentOrder = this.orderRepository.findById(orderId).get();
        } catch (Exception e) {
            throw new BusinessException(messageSourceService.getMessage(Messages.Order.orderNotFound));
        }
        return currentOrder;
    }
}
