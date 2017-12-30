package com.godlumen.controller;


import com.godlumen.service.SysPermissionInitService;
import com.godlumen.shiro.ShiroService;
import org.apache.shiro.SecurityUtils;
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String userLogin(HttpServletRequest request){
        String mobile=(String)request.getAttribute("mobile");
        String password=(String) request.getAttribute("password");
        Boolean rememberMe=(Boolean) request.getAttribute("rememberMe");
        String exception = (String) request.getAttribute("shiroLoginFailure");
//        Map<String,Object> resultMap=new LinkedHashMap<String,Object>();
//        UsernamePasswordToken token=new UsernamePasswordToken(mobile,password,rememberMe);
//        SecurityUtils.getSubject().login(token);
//        try{
//            resultMap.put("status",200);
//            resultMap.put("message","登录成功！");
//        }catch (Exception e){
//            resultMap.put("status",500);
//            resultMap.put("message","登录失败！");
//        }
//        return resultMap;
        return mobile+password+rememberMe;
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
