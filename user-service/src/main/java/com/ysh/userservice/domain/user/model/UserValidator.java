package com.ysh.userservice.domain.user.model;

import com.ysh.userservice.domain.user.entity.User;

import java.security.InvalidParameterException;

public class UserValidator {

    public static void check(User user) {
        if (!isValidId(user.getId()) || !isValidPassword(user.getPassword()) || !isValidName(user.getName()) || !isValidEmail(user.getEmail()))
            throw new InvalidParameterException();
    }

    private static boolean isValidId(String id) {
        return id != null && !id.trim().isEmpty() && id.length() >= 3;
    }

    private static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= 3;
    }

    private static boolean isValidEmail(String email) {
        return email != null && email.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
    }

    private static boolean isValidPassword(String password) {
        return password != null && !password.trim().isEmpty() && password.length() >= 8;
    }
}
