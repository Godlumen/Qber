package com.godlumen.service;

import com.godlumen.model.SysPermissionInit;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SysPermissionInitService {
    List<SysPermissionInit> selectAllPermissionInit();
    int updatePermissionInitById(long id,String permissionInit);
}
