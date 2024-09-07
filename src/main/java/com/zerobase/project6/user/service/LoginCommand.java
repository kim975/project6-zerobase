package com.zerobase.project6.user.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginCommand {

    private String loginId;
    private String password;

}
