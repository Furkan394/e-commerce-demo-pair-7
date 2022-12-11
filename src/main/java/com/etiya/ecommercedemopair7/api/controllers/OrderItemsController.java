package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IOrderItemService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.dtos.OrderItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "order-items")
public class OrderItemsController {

    private IOrderItemService orderItemService;

    @Autowired
    public OrderItemsController(IOrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/get-order-item-dto")
    public ResponseEntity<DataResult<List<OrderItemDto>>> getOrderItemDto() {
        return ResponseEntity.ok(orderItemService.getOrderItemDto());
    }
}
