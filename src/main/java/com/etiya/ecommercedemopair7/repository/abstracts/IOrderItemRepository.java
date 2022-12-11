package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.entities.concretes.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query("select new com.etiya.ecommercedemopair7.entities.dtos.OrderItemDto (oi.id,oi.quantity," +
            "oi.itemTotalPrice,o.orderNumber,o.totalPrice,o.orderDate,d.name,oa.address,ia.address," +
            "p.name,sp.description,sp.imageUrl,sp.stock,sp.unitPrice) from OrderItem oi inner join" +
            " oi.order o inner join o.deliveryOption d inner join o.orderAddress oa inner join o.invoiceAddress ia " +
            "inner join oi.product p inner join p.sellerProducts sp ")
    List<OrderItem> getOrderItemDto();
}
