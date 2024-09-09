package com.zerobase.project6.user.service;

import lombok.Builder;
import lombok.Data;

/**
 * service에서 사용하는 DTO
 */
@Data
@Builder
public class LoginCommand {

    private String loginId;
    private String password;

}
