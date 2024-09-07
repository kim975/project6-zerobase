package com.zerobase.project6.security;

import com.zerobase.project6.user.domain.model.common.UserRole;

import java.util.List;

public interface UserSecurity {

    String getUserToken();
    String getUserType();
    List<UserRole> getUserRoles();
}
