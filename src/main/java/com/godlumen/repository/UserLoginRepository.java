package com.godlumen.repository;

import com.godlumen.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin,Long> {
    public UserLogin findByMobile(String mobile);
}
