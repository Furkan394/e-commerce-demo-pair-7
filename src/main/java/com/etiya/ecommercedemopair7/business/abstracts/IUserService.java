package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetUserResponse;
import com.etiya.ecommercedemopair7.entities.concretes.User;

import java.util.List;

public interface IUserService {
    List<GetAllUserResponse> getAll();
    GetUserResponse getById(int userId);
    User getByUserId(int userId);
}
