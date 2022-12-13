package com.etiya.ecommercedemopair7.entities.concretes;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "id")
public class CorporateCustomer extends Customer {

    @Column(name = "name")
    private String name;

    @Column(name = "tax_number")
    private String taxNumber;
}
