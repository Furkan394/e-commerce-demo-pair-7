package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IAddressService;
import com.etiya.ecommercedemopair7.business.abstracts.IStreetService;
import com.etiya.ecommercedemopair7.business.abstracts.IUserService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.addresses.AddAddressRequest;
import com.etiya.ecommercedemopair7.business.response.addresses.AddAddressResponse;
import com.etiya.ecommercedemopair7.business.response.addresses.GetAddressResponse;
import com.etiya.ecommercedemopair7.business.response.addresses.GetAllAddressResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.Address;
import com.etiya.ecommercedemopair7.entities.concretes.Street;
import com.etiya.ecommercedemopair7.entities.concretes.User;
import com.etiya.ecommercedemopair7.repository.abstracts.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressManager implements IAddressService {

    private IAddressRepository addressRepository;
    private IStreetService streetService;
    private IUserService userService;
    private IModelMapperService mapper;

    @Autowired
    AddressManager(IAddressRepository addressRepository, IStreetService streetService, IUserService userService, IModelMapperService mapper) {
        this.addressRepository = addressRepository;
        this.streetService = streetService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllAddressResponse> getAll() {
        List<Address> addresses = addressRepository.findAll();
        List<GetAllAddressResponse> response = addresses.stream()
                .map(address -> mapper.forResponse().map(address, GetAllAddressResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GetAddressResponse getById(int addressId) {
        Address address = checkIfAddressExistsById(addressId);
        GetAddressResponse response = mapper.forResponse().map(address, GetAddressResponse.class);
        return response;
    }

    @Override
    public Address getByAddressId(int addressId) {
        return checkIfAddressExistsById(addressId);
    }

    @Override
    public AddAddressResponse add(AddAddressRequest addAddressRequest) {

        getUser(addAddressRequest.getUserId());
        getStreet(addAddressRequest.getStreetId());

        Address address = mapper.forRequest().map(addAddressRequest, Address.class);

        Address savedAddress = addressRepository.save(address);

        AddAddressResponse response = mapper.forResponse().map(savedAddress, AddAddressResponse.class);

        return response;
    }

    private User getUser(int userId) {
        User user = userService.getByUserId(userId);
        return user;
    }

    private Street getStreet(int streetId) {
        Street street = streetService.getByStreetId(streetId);
        return street;
    }

    private Address checkIfAddressExistsById(int addressId) {
        Address currentAddress;
        try {
            currentAddress = this.addressRepository.findById(addressId).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.Address.addressNotFound);
        }
        return currentAddress;
    }
}
