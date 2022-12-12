package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IUserService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.users.AddUserRequest;
import com.etiya.ecommercedemopair7.business.response.users.AddUserResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetAllUserResponse;
import com.etiya.ecommercedemopair7.business.response.users.GetUserResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "users")
public class UsersController {

    private IUserService userService;

    @Autowired
    public UsersController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllUserResponse>>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetUserResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddUserResponse>> add(@RequestBody @Valid AddUserRequest addUserRequest) {
        return new ResponseEntity<>(userService.add(addUserRequest), HttpStatus.CREATED);
    }
}
