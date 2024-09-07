package com.zerobase.project6.user.controller;

import com.zerobase.project6.user.service.LoginCommand;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {

    private String loginId;
    private String password;

    public LoginCommand toCommand() {
        return LoginCommand.builder()
                .loginId(loginId)
                .password(password)
                .build();
    }

}
