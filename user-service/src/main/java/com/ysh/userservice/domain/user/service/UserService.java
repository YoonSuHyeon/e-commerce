package com.ysh.userservice.domain.user.service;

import com.ysh.userservice.domain.user.exception.InvalidUserException;
import com.ysh.userservice.domain.user.model.User;
import com.ysh.userservice.domain.user.model.UserType;
import com.ysh.userservice.infrastructure.persistence.UserRepository;
import com.ysh.userservice.infrastructure.security.JwtProvider;
import com.ysh.userservice.presentation.dto.LoginRequest;
import com.ysh.userservice.presentation.dto.LoginResponse;
import com.ysh.userservice.presentation.dto.UserRequest;
import com.ysh.userservice.presentation.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(UserRequest userDTO) {

        //유효성 검사
        if (!userDTO.isValid())
            throw new InvalidUserException();

        String name = userDTO.getName();
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        UserType type = userDTO.getType();

        // 패스워드 암호화
        password = passwordEncoder.encode(password);

        // 유저 생성 및 DB 저장
        User newUser = new User(name, email, password, type);
        userRepository.save(newUser);
    }


    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("Not Exist User"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid Password");

        return new LoginResponse(user.getName(), user.getEmail(), jwtProvider.createToken(user.getEmail()));
    }

    private User getUser(String email) {
        //TODO email 형식 검사
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Not Exist User"));
    }

    public UserResponse getUserResponse(String email) {
        User user = getUser(email);

        return new UserResponse(user.getName(), user.getEmail());
    }
}
