package com.godlumen.shiro;

import com.godlumen.model.SysPermission;
import com.godlumen.model.SysRole;
import com.godlumen.model.UserLogin;
import com.godlumen.service.UserLoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserLoginService userLoginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserLogin userLogin = (UserLogin) principals.getPrimaryPrincipal();
        for (SysRole role : userLogin.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }
    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
//        UsernamePasswordToken token=(UsernamePasswordToken) authcToken;
//        String mobile=token.getUsername();
//        String password=String.valueOf(token.getPassword());
        //获取用户的输入的账号.
        String mobile = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过mobile从数据库中查找 UserLogin对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserLogin userLogin = userLoginService.findByMobile(mobile);
        System.out.println("----->>UserLogin=" + userLogin);
        if (userLogin == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userLogin, //用户名
                userLogin.getPassword(), //密码
                ByteSource.Util.bytes(userLogin.getCredentialsSalt()),//salt=mobile+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}