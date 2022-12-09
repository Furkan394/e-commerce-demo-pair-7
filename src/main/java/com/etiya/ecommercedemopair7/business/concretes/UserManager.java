package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IUserService;
import com.etiya.ecommercedemopair7.business.constants.Messages;
import com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetUserResponse;
import com.etiya.ecommercedemopair7.core.utilities.mapping.IModelMapperService;
import com.etiya.ecommercedemopair7.entities.concretes.User;
import com.etiya.ecommercedemopair7.repository.abstracts.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements IUserService {
    private IUserRepository userRepository;
    private IModelMapperService mapper;

    @Autowired
    UserManager(IUserRepository userRepository, IModelMapperService mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetAllUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<GetAllUserResponse> response = users.stream()
                .map(user -> mapper.forResponse().map(user, GetAllUserResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public GetUserResponse getById(int userId) {
        User user = checkIfUserExistsById(userId);
        GetUserResponse response = mapper.forResponse().map(user, GetUserResponse.class);
        return response;
    }

    @Override
    public User getByUserId(int userId) {
        return checkIfUserExistsById(userId);
    }

    private User checkIfUserExistsById(int userId) {
        User currentUser;
        try {
            currentUser = this.userRepository.findById(userId).get();
        } catch (Exception e) {
            throw new RuntimeException(Messages.User.userNotFound);
        }
        return currentUser;
    }
}
