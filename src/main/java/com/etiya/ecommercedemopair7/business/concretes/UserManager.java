package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IUserService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.request.users.AddUserRequest;
import com.etiya.ecommercedemopair7.business.response.users.AddUserResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetUserResponse;
import com.etiya.ecommercedemopair7.core.utilities.exceptions.BusinessException;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.core.utilities.messages.IMessageSourceService;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.core.utilities.results.SuccessDataResult;
import com.etiya.ecommercedemopair7.entities.concretes.User;
import com.etiya.ecommercedemopair7.repository.abstracts.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements IUserService {
    private IUserRepository userRepository;
    private IModelMapperService mapper;
    private IMessageSourceService messageSourceService;

    @Autowired
    UserManager(IUserRepository userRepository, IModelMapperService mapper, IMessageSourceService messageSourceService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public DataResult<List<GetAllUserResponse>> getAll() {
        List<User> users = userRepository.findAll();
        List<GetAllUserResponse> response = users.stream()
                .map(user -> mapper.forResponse().map(user, GetAllUserResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.User.usersListed));
    }

    @Override
    public DataResult<GetUserResponse> getById(int userId) {
        User user = checkIfUserExistsById(userId);
        GetUserResponse response = mapper.forResponse().map(user, GetUserResponse.class);
        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.User.userReceived));
    }

    @Override
    public DataResult<User> getByUserId(int userId) {
        return new SuccessDataResult<>(checkIfUserExistsById(userId), messageSourceService.getMessage(Messages.User.userReceived));
    }

    @Override
    public DataResult<AddUserResponse> add(AddUserRequest addUserRequest) {
        User user = mapper.forRequest().map(addUserRequest, User.class);

        User savedUser = userRepository.save(user);

        AddUserResponse response = mapper.forResponse().map(savedUser, AddUserResponse.class);

        return new SuccessDataResult<>(response, messageSourceService.getMessage(Messages.User.userAdded));
    }

    @Override
    public DataResult<Page<GetAllUserResponse>> getAllUsersWithPage(Pageable pageable) {
        return new SuccessDataResult<>(userRepository.findAllUsersWithPage(pageable));
    }

    private User checkIfUserExistsById(int userId) {
        User currentUser;
        try {
            currentUser = this.userRepository.findById(userId).get();
        } catch (Exception e) {
            throw new BusinessException(messageSourceService.getMessage(Messages.User.userNotFound));
        }
        return currentUser;
    }

}
