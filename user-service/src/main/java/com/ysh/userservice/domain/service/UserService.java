package com.ysh.userservice.domain.service;

import com.ysh.userservice.domain.entity.User;
import com.ysh.userservice.domain.exception.InvalidUserException;
import com.ysh.userservice.domain.model.UserType;
import com.ysh.userservice.domain.repositoy.UserRepository;
import com.ysh.userservice.presentation.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signUp(UserDTO userDTO) {

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


}
