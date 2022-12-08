package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.response.users.GetUserResponse;
import com.etiya.ecommercedemopair7.entities.concretes.User;

public interface IUserService {
    GetUserResponse getById(int userId);
    User getByUserId(int userId);
}
