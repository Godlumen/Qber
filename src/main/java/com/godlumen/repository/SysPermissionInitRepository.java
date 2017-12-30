package com.godlumen.repository;

import com.godlumen.model.SysPermissionInit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysPermissionInitRepository extends JpaRepository<SysPermissionInit,Long>{
    List<SysPermissionInit> findAll();
    @Transactional
    @Modifying
    @Query("update SysPermissionInit spi set spi.permissionInit = ?2 where spi.id = ?1")
    int updateById(long id,String permissionInit);
}
