package com.etiya.ecommercedemopair7.business.request.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String email;
    private String password;
}
