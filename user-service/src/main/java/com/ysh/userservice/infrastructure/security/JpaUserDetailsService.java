package com.ysh.userservice.infrastructure.security;

import com.ysh.userservice.domain.user.entity.User;
import com.ysh.userservice.domain.user.exception.NotExistUserException;
import com.ysh.userservice.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User findUser = userRepository.findById(id).orElseThrow(NotExistUserException::new);

        return new CustomUserDetail(findUser);
    }
}
