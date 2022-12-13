package com.etiya.ecommercedemopair7.entities.concretes;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends User {

    @Column(name = "number")
    private String number;

    @OneToOne(mappedBy = "customer")
    private Basket basket;
}
