package com.ysh.userservice.presentation.user;


import com.ysh.userservice.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String signUp(@RequestBody UserDTO userDTO) {

        userService.signUp(userDTO);
        return "회원가입 완료";
    }
}
