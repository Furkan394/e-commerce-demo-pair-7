package com.etiya.ecommercedemopair7.business.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserResponse {
    private int id;
    private String email;
}

