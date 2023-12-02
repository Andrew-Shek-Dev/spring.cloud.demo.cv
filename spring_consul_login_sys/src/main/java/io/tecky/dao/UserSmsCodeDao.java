package io.tecky.dao;

import io.tecky.data.UserSmsCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserSmsCodeDao {
    @Insert("insert into user_sms_code (mobile_no,sms_code,send_time,create_time) values (#{mobile_no},#{sms_code},#{send_time},#{create_time})")
    public void insert(UserSmsCode userSmsCode);

    @Select("select * from user_sms_code where mobile_no=#{mobile_no} order by create_time limit 1")
    public UserSmsCode selectByMobileNo(UserSmsCode userSmsCode);
}
