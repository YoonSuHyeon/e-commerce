package com.ysh.userservice.presentation.user;

import com.ysh.userservice.domain.model.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserDTOTest {

    @Test
    public void test_valid() {
        String name = "YoonSuHyeon";
        String email = "96-05-03@naver.com";
        String password = "12345678";
        UserType type = UserType.A;

        UserDTO userDTO = new UserDTO(name, email, password, type);
        Assertions.assertTrue(userDTO.isValid());

    }

    @Test
    public void test_InvalidName() {
        String name = "Yo";
        String email = "96-05-03@naver.com";
        String password = "12345678";
        UserType type = UserType.A;

        UserDTO userDTO = new UserDTO(name, email, password, type);
        Assertions.assertFalse(userDTO.isValid());

    }

    @Test
    public void test_InvalidEmail() {
        String name = "YoonSuHyeon";
        String email = "96-05-03@@naver.com";
        String password = "12345678";
        UserType type = UserType.A;

        UserDTO userDTO = new UserDTO(name, email, password, type);
        Assertions.assertFalse(userDTO.isValid());

    }

    @Test
    public void test_InvalidPassword() {
        String name = "YoonSuHyeon";
        String email = "96-05-03@naver.com";
        String password = "1234567";
        UserType type = UserType.A;

        UserDTO userDTO = new UserDTO(name, email, password, type);
        Assertions.assertFalse(userDTO.isValid());

    }


}