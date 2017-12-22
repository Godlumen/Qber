package com.godlumen.service;

import com.godlumen.model.UserLogin;

public interface UserLoginService {
    public UserLogin findByMobile(String mobile);
}
