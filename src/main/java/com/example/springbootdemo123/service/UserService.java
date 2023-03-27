package com.example.springbootdemo123.service;

import com.example.springbootdemo123.entity.UserBean;
import com.example.springbootdemo123.mapper.UserMapper;
import com.example.springbootdemo123.sha256.PasswordEncoder;
import lombok.Data;
import lombok.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;


@Service
public class UserService {
    //将dao层属性注入service层
   @Resource
    private UserMapper userMapper;

   public UserBean findUserByNameAndPassword(String name, String password){
       return userMapper.getUserByNameAndPassword(name,password);
   }

   public void saveUser(String name, String password) throws NoSuchAlgorithmException {
       String hashedPassword = PasswordEncoder.encode(password);
       userMapper.saveUser(name, password);
   }

}
