package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.business.response.orders.GetAllOrderResponse;
import com.etiya.ecommercedemopair7.entities.concretes.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
    @Query("select new com.etiya.ecommercedemopair7.business.response.orders.GetAllOrderResponse " +
            "(o.id,o.orderNumber,o.totalPrice,o.orderDate,o.deliveryOption.id,o.orderAddress.id,o.invoiceAddress.id) from Order o")
    Page<GetAllOrderResponse> findAllOrdersWithPage(Pageable pageable);
}
