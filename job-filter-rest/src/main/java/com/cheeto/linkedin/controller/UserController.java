package com.cheeto.linkedin.controller;


import com.cheeto.jobfilter.api.UsersApi;
import com.cheeto.jobfilter.model.CreateUserRequest;
import com.cheeto.jobfilter.model.CreateUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UsersApi {

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {
        return ResponseEntity.status(201).body(null);
    }

}
