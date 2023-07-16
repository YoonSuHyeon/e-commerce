package com.ysh.userservice.presentation.user;


import com.ysh.userservice.domain.model.UserType;
import lombok.Getter;

@Getter
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private UserType type;
}
