package com.etiya.ecommercedemopair7.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
public class Customer extends User {

    @Column(name = "number")
    private String number;

    @OneToMany(mappedBy = "customer")
    private List<Basket> baskets;
}
