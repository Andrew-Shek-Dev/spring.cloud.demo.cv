package io.tecky.dao;

import io.tecky.data.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserInfoDao {
    @Select("select * from user_info where mobile_no=#{mobile_no} limit 1")
    public UserInfo selectByMobile(UserInfo user);

    @Insert("insert into user_info (user_id,mobile_no,password,is_login,login_time,create_time) values (#{user_id},#{mobile_no},#{password},#{is_login},#{login_time},#{create_time})")
    public void insert(UserInfo user);

    @Update("update user_info set is_login = #{is_login},login_time=#{login_time}")
    public void update(UserInfo user);
}
