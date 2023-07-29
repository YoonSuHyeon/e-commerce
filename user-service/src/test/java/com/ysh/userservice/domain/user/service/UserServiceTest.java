package com.ysh.userservice.domain.user.service;

import com.ysh.userservice.domain.user.entity.User;
import com.ysh.userservice.domain.user.model.UserType;
import com.ysh.userservice.infrastructure.persistence.UserRepository;
import com.ysh.userservice.infrastructure.security.JwtProvider;
import com.ysh.userservice.presentation.dto.LoginRequest;
import com.ysh.userservice.presentation.dto.LoginResponse;
import com.ysh.userservice.presentation.dto.SignUpRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Spy
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtProvider jwtProvider;

    @Test
    public void test_signUp() {

        //given
        SignUpRequest signUpRequest = new SignUpRequest("TEST1", "password", "name", "1234@naver.com", UserType.B);

        //mocking
        when(userRepository.countById(any())).thenReturn(0L);
        when(encoder.encode(any())).thenReturn("ENCODE");

        //when
        User newUser = userService.signUp(signUpRequest);

        //then
        assertEquals(newUser.getId(), signUpRequest.getId());
        assertEquals(newUser.getPassword(), "ENCODE");
        assertEquals(newUser.getName(), signUpRequest.getName());
        assertEquals(newUser.getEmail(), signUpRequest.getEmail());
        assertEquals(newUser.getType(), signUpRequest.getType());
    }

    @Test
    public void test_login() {

        //given
        String id = "id";
        String password = "password";
        String encodePassword = "encode";
        String tokenCode = "tokenCode";
        LoginRequest loginRequest = new LoginRequest(id, password);
        User user = new User(id, encodePassword, "name", "1234@naver.com", UserType.A);

        //mocking
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(encoder.matches(password, encodePassword)).thenReturn(true);
        when(jwtProvider.createToken(id)).thenReturn(tokenCode);

        //when
        LoginResponse login = userService.login(loginRequest);

        //then
        Assertions.assertEquals(login.getTokenCode(), tokenCode);
    }
}