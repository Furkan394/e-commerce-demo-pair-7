package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IAddressService;
import com.etiya.ecommercedemopair7.business.abstracts.IStreetService;
import com.etiya.ecommercedemopair7.business.abstracts.IUserService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.addresses.AddAddressRequest;
import com.etiya.ecommercedemopair7.business.response.addresses.AddAddressResponse;
import com.etiya.ecommercedemopair7.business.response.addresses.GetAddressResponse;
import com.etiya.ecommercedemopair7.business.response.addresses.GetAllAddressResponse;
import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Address;
import com.etiya.ecommercedemopair7.entities.concretes.Street;
import com.etiya.ecommercedemopair7.entities.concretes.User;
import com.etiya.ecommercedemopair7.entities.dtos.AddressDto;
import com.etiya.ecommercedemopair7.repository.abstracts.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressManager implements IAddressService {

    private IAddressRepository addressRepository;
    private IStreetService streetService;
    private IUserService userService;
    private IModelMapperService mapper;
    private IMessageSourceService messageSourceService;

    @Autowired
    AddressManager(IAddressRepository addressRepository, IStreetService streetService, IUserService userService,
                   IModelMapperService mapper, IMessageSourceService messageSourceService) {
        this.addressRepository = addressRepository;
        this.streetService = streetService;
        this.userService = userService;
        this.mapper = mapper;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public DataResult<List<GetAllAddressResponse>> getAll() {
        List<Address> addresses = addressRepository.findAll();
        List<GetAllAddressResponse> response = addresses.stream()
                .map(address -> mapper.forResponse().map(address, GetAllAddressResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Address.addressesListed));
    }

    @Override
    public DataResult<GetAddressResponse> getById(int addressId) {
        Address address = checkIfAddressExistsById(addressId);
        GetAddressResponse response = mapper.forResponse().map(address, GetAddressResponse.class);
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Address.addressReceived));
    }

    @Override
    public Address getByAddressId(int addressId) {
        return checkIfAddressExistsById(addressId);
    }

    @Override
    public DataResult<AddAddressResponse> add(AddAddressRequest addAddressRequest) {

        getUser(addAddressRequest.getUserId());
        getStreet(addAddressRequest.getStreetId());

        Address address = mapper.forRequest().map(addAddressRequest, Address.class);

        Address savedAddress = addressRepository.save(address);

        AddAddressResponse response = mapper.forResponse().map(savedAddress, AddAddressResponse.class);

        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Address.addressAdded));
    }

    @Override
    public DataResult<List<AddressDto>> getAddressDto() {
        //TODO: Countryname null
        List<Address> addresses = this.addressRepository.findAll();
        List<AddressDto> response = addresses.stream()
                .map(address -> mapper.forResponse().map(address, AddressDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.Address.addressesListed));
    }

    @Override
    public Address getByUserId(int userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public DataResult<Page<List<AddressDto>>>  getAddressDtoWithPage(Pageable pageable) {
        return new SuccessDataResult<>(addressRepository.getAddressDtoWithPage(pageable), messageSourceService.getMessage(Messages.Address.addressesListed));
    }

    private DataResult<User> getUser(int userId) {
        DataResult<User> user = userService.getByUserId(userId);
        return user;
    }

    private DataResult<Street> getStreet(int streetId) {
        DataResult<Street> street = streetService.getByStreetId(streetId);
        return street;
    }

    private Address checkIfAddressExistsById(int addressId) {
        Address currentAddress;
        try {
            currentAddress = this.addressRepository.findById(addressId).get();
        } catch (Exception e) {
            throw new BusinessException(messageSourceService.getMessage(Messages.Address.addressNotFound));
        }
        return currentAddress;
    }
}
