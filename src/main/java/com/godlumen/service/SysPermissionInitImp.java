package com.godlumen.service;

import com.godlumen.model.SysPermissionInit;
import com.godlumen.repository.SysPermissionInitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("sysPermissionInitService")
public class SysPermissionInitImp implements SysPermissionInitService{
    @Autowired
    private SysPermissionInitRepository sysPermissionInitRepository;
    @Override
    public List<SysPermissionInit> selectAllPermissionInit() {
        return sysPermissionInitRepository.findAll();
    }

    @Override
    public int updatePermissionInitById(long id, String permissionInit) {
        return sysPermissionInitRepository.updateById(id,permissionInit);
    }
}
