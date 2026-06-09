package com.cheeto.linkedin.controller;


import com.cheeto.jobfilter.api.UsersApi;
import com.cheeto.jobfilter.model.CreateUserRequest;
import com.cheeto.jobfilter.model.CreateUserResponse;
import com.cheeto.linkedin.service.UserService;
import com.cheeto.linkedin.service.mappers.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {
        Long userId = userService.createUser(UserDTOMapper.toDao(createUserRequest.getUser()));
        return ResponseEntity.ok(CreateUserResponse.builder().id(userId).build());
    }

}
