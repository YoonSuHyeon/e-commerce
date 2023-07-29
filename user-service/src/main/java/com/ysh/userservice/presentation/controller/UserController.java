package com.ysh.userservice.presentation.controller;


import com.ysh.userservice.domain.user.annotation.LoginUser;
import com.ysh.userservice.domain.user.entity.User;
import com.ysh.userservice.domain.user.service.UserService;
import com.ysh.userservice.presentation.dto.LoginRequest;
import com.ysh.userservice.presentation.dto.LoginResponse;
import com.ysh.userservice.presentation.dto.SignUpRequest;
import com.ysh.userservice.presentation.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request) {
        userService.signUp(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@LoginUser User user) {
        return ResponseEntity.ok(user.toUserResponse());
    }


}
