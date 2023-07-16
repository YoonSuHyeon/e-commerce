package com.ysh.userservice.domain.service;

import com.ysh.userservice.domain.entity.User;
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

        //TODO userDTO에 대한 유효성 검사
        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setType(userDTO.getType());

        // DB 저장
        userRepository.save(user);
    }
}
