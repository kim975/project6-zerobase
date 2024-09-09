package com.zerobase.project6.user.controller;

import com.zerobase.project6.user.service.LoginCommand;
import lombok.Builder;
import lombok.Data;

/**
 * client request, response DTO
 *
 * 로그인용 DTO
 */
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
