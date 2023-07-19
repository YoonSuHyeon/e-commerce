package com.ysh.userservice.presentation.controller;


import com.ysh.userservice.domain.user.service.UserService;
import com.ysh.userservice.presentation.dto.LoginRequest;
import com.ysh.userservice.presentation.dto.LoginResponse;
import com.ysh.userservice.presentation.dto.UserRequest;
import com.ysh.userservice.presentation.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserResponse(email));
    }

    @PostMapping
    public ResponseEntity<UserRequest> signUp(@RequestBody UserRequest userRequest) {
        userService.signUp(userRequest);
        return ResponseEntity.ok(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }


}
