package com.ysh.userservice.domain.user.entity;


import com.ysh.userservice.domain.user.model.UserType;
import com.ysh.userservice.presentation.dto.SignUpRequest;
import com.ysh.userservice.presentation.dto.UserResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {

    @Id
    @Column(nullable = false)
    private String id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;

    public User(String id, String password, String name, String email, UserType type) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void encodePassword(PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }


    public UserResponse toUserResponse() {
        return new UserResponse(id, name, email, type);
    }
}
