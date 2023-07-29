package com.ysh.userservice.presentation.dto;

import com.ysh.userservice.domain.user.entity.User;
import com.ysh.userservice.domain.user.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;

/**
 * TODO 추후 유효성 검사하는 부분 수정 필요
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpRequest {
    private String id;
    private String password;
    private String name;
    private String email;
    private UserType type;

    public User toUser() {
        return new User(id, password, name, email, type);
    }
}
