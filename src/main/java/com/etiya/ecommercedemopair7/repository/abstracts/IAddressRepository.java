package com.etiya.ecommercedemopair7.repository.abstracts;

import com.etiya.ecommercedemopair7.entities.concretes.Address;
import com.etiya.ecommercedemopair7.entities.dtos.AddressDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
    @Query("Select new com.etiya.ecommercedemopair7.entities.dtos.AddressDto  (a.id,a.title,a.address,s.name," +
            "d.name,t.name,c.name,co.name,u.email) From Address a inner join a.street s inner join s.district d inner join" +
            " d.town t inner join t.city c inner join c.country co inner join a.user u")
    List<AddressDto> getAddressDto();
}
