package com.ysh.userservice.domain.user.service;

import com.ysh.userservice.domain.user.entity.User;
import com.ysh.userservice.domain.user.exception.AlreadyExistUserException;
import com.ysh.userservice.domain.user.exception.IncorrectPasswordException;
import com.ysh.userservice.domain.user.exception.NotExistUserException;
import com.ysh.userservice.domain.user.model.UserValidator;
import com.ysh.userservice.infrastructure.persistence.UserRepository;
import com.ysh.userservice.infrastructure.security.JwtProvider;
import com.ysh.userservice.presentation.dto.LoginRequest;
import com.ysh.userservice.presentation.dto.LoginResponse;
import com.ysh.userservice.presentation.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    /**
     * TODO 같은 ID 동시 요청 발생하는 경우 (Duplicate Key Exception)
     *
     * @param request
     */
    @Transactional
    public User signUp(SignUpRequest request) {
        //중복 ID 검사
        if (userRepository.countById(request.getId()) > 0)
            throw new AlreadyExistUserException();

        //요청 값 체크
        User newUser = request.toUser();
        UserValidator.check(newUser);

        //비밀번호 암호화 후 DB 저장
        newUser.encodePassword(encoder);
        userRepository.save(newUser);

        return newUser;
    }

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        // ID로 조회
        User findUser = userRepository.findById(loginRequest.getId()).orElseThrow(NotExistUserException::new);

        if (!encoder.matches(loginRequest.getPassword(), findUser.getPassword()))
            throw new IncorrectPasswordException();

        String tokenCode = jwtProvider.createToken(findUser.getId());
        return new LoginResponse(tokenCode);
    }
}
