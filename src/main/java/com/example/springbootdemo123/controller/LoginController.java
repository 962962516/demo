package com.example.springbootdemo123.controller;

import com.example.springbootdemo123.entity.UserBean;
import com.example.springbootdemo123.filter.AuthHandlerInterceptor;
import com.example.springbootdemo123.service.UserService;
import com.example.springbootdemo123.sha256.PasswordEncoder;
import com.example.springbootdemo123.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
@Slf4j
@Controller
@Api(tags = "实现登陆注册",description = "登陆和注册")
public class LoginController{

    //将Service注入Web层
    @Resource
    UserService userService;

    @ApiOperation("登陆页面")
    //实现登陆
    @RequestMapping("/login")
    public String show(){
        return "login";
    }

    @ApiOperation("实现登陆")
    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,@RequestParam("password") String password)
            throws NoSuchAlgorithmException {
        String hashedPassword = PasswordEncoder.encode(password);
        UserBean userBean = userService.findUserByNameAndPassword(username,hashedPassword);
        if(userBean != null){

            return "success";
        }else{
            return "error";
        }

    }

    @ApiOperation("注册页面")
    //注册
    @RequestMapping ("/signup")
    public String ShowSignUpPage() {
        return "signup";
    }

    @ApiOperation("实现注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String signUp(@RequestParam("username") String username, @RequestParam("password") String password)
            throws NoSuchAlgorithmException{
        String hashedPassword = PasswordEncoder.encode(password);
        userService.saveUser(username,hashedPassword);
        return "success";
    }

}

