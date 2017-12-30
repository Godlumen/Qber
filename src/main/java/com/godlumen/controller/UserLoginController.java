package com.godlumen.controller;


import com.godlumen.service.SysPermissionInitService;
import com.godlumen.shiro.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class UserLoginController {

    @Autowired
    private SysPermissionInitService sysPermissionInitService;
    @Autowired
    private ShiroService shiroService;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/login")
    public String userLogin(HttpServletRequest request, Map<String, Object> map) throws Exception{
        String mobile=(String)request.getAttribute("mobile");
        String password=(String) request.getAttribute("password");
        Boolean rememberMe=(Boolean) request.getAttribute("rememberMe");
        UsernamePasswordToken token=new UsernamePasswordToken(mobile,password,rememberMe);
        SecurityUtils.getSubject().login(token);
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理
        return "/login";
    }
    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("------没有权限-------");
        return "403";
    }

    @RequestMapping("/static")
    @ResponseBody
    public String anno() {
//        sysPermissionInitService.updatePermissionInitById(1,"authc");
        shiroService.updatePermission();
        return "anon";
    }

    @RequestMapping("/ssl")
    @ResponseBody
    public String ssl() {
        return "ssl";
    }
}
