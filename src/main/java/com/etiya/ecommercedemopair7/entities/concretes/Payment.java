package com.etiya.ecommercedemopair7.entities.concretes;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "delivery_options_id")
    private DeliveryOption deliveryOption;

    @ManyToOne
    @JoinColumn(name = "order_address_id")
    private Address orderAddress;

    @ManyToOne
    @JoinColumn(name = "invoice_address_id")
    private Address invoiceAddress;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "is_verified")
    private boolean isVerified;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Order order;
}
