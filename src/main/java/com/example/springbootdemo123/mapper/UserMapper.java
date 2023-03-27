package com.example.springbootdemo123.mapper;

import com.example.springbootdemo123.entity.UserBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //查询，实现登陆功能
    @Select("SELECT * FROM user WHERE name = #{name} AND password = #{password}")
    UserBean  getUserByNameAndPassword(@Param("name") String name, @Param("password") String password);

    //增加 可以实现注册功能
    @Insert("insert into user(name,password)values(#{name},#{password})")
    void saveUser(@Param("name") String name, @Param("password") String password);
}
