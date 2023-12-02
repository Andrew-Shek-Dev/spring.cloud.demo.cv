package com.example.mybatis.dao;

import com.example.mybatis.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html
@Mapper
public interface UserDao {
    @Select("select * from \"user\"")
//    @Results({
//            @Result(property = "username",column = "user_name")
//    })
    public List<User> getAllUser();

    @Insert("insert into \"user\" (username,password) values (#{username},#{password})")
    public void addUser(User user);

    @Update("update \"user\" set password=#{password} where username=#{username}")
    public void updateUser(User user);

    @Delete("delete from \"user\" where username=#{username}")
    public void deleteUser(User user);
}
