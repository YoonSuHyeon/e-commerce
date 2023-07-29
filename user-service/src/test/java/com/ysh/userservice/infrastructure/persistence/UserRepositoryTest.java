package com.ysh.userservice.infrastructure.persistence;

import com.ysh.userservice.domain.user.entity.User;
import com.ysh.userservice.domain.user.model.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void test_save() {
        //given
        User user = new User("TEST", "TEST", "TEST", "TEST", UserType.B);

        //when
        userRepository.save(user);

        //then
        Optional<User> findOpUser = userRepository.findById("TEST");
        Assertions.assertTrue(findOpUser.isPresent());

        User findUser = findOpUser.get();
        Assertions.assertEquals(findUser.getId(), user.getId());
        Assertions.assertEquals(findUser.getPassword(), user.getPassword());
        Assertions.assertEquals(findUser.getName(), user.getName());
        Assertions.assertEquals(findUser.getEmail(), user.getEmail());
        Assertions.assertEquals(findUser.getType(), user.getType());
    }

    @Test
    public void test_update() {
        //given
        User user = new User("TEST", "TEST", "TEST", "TEST", UserType.B);

        //when
        userRepository.save(user);

        user.changeName("TEST2");
        userRepository.save(user);
        //then
        User findUser = userRepository.findById("TEST").get();
        Assertions.assertEquals(findUser.getName(), "TEST2");
    }

}