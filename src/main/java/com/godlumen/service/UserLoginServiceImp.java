package com.godlumen.service;

import com.godlumen.model.UserLogin;
import com.godlumen.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userLoginService")
public class UserLoginServiceImp implements UserLoginService {
    @Autowired
    private UserLoginRepository userLoginRepository;
    @Override
    public UserLogin findByMobile(String mobile) {
        return userLoginRepository.findByMobile(mobile);
    }
}
