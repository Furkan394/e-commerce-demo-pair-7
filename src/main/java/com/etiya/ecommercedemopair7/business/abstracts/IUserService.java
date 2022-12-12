package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.request.users.AddUserRequest;
import com.etiya.ecommercedemopair7.business.response.users.AddUserResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetUserResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.User;

import javax.xml.crypto.Data;
import java.util.List;

public interface IUserService {
    DataResult<List<GetAllUserResponse>> getAll();
    DataResult<GetUserResponse> getById(int userId);
    DataResult<User> getByUserId(int userId);
    DataResult<AddUserResponse> add(AddUserRequest addUserRequest);
}
