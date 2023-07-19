package com.ysh.userservice.presentation.dto;


import com.ysh.userservice.domain.user.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private UserType type;

    /**
     * 유효성 검사
     *
     * @return
     */
    public boolean isValid() {
        return isValidName() && isValidEmail() && isValidPassword();
    }

    private boolean isValidName() {
        return name != null && !name.trim().isEmpty() && name.length() >= 3;
    }

    /**
     * TODO 추후 이메일 형식 관련 확인 필요
     *
     * @return
     */
    private boolean isValidEmail() {
        return email != null && email.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
    }

    private boolean isValidPassword() {
        return password != null && !password.trim().isEmpty() && password.length() >= 8;
    }
}
