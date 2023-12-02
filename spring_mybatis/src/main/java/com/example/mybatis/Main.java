package com.example.mybatis;

import com.example.mybatis.dao.UserDao;
import com.example.mybatis.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private final UserDao userDao;

    Main(UserDao userDao){
        this.userDao = userDao;

        //Test here
        //Print First User
        System.out.println(this.userDao.getAllUser().get(0).getUsername() + " " + this.userDao.getAllUser().get(0).getPassword());

        //Update User
        User updateUser = this.userDao.getAllUser().get(0);
        updateUser.setPassword("abcdef");
        this.userDao.updateUser(updateUser);
        System.out.println(this.userDao.getAllUser().get(0).getUsername() + " " + this.userDao.getAllUser().get(0).getPassword());

        //Add User
        User user = new User();
        user.setUsername("user");
        user.setPassword("123456");
        this.userDao.addUser(user);
        System.out.println(this.userDao.getAllUser().get(1).getUsername() + " " + this.userDao.getAllUser().get(1).getPassword());

        //Delete User
        this.userDao.deleteUser(user);
        System.out.println(this.userDao.getAllUser().get(0).getUsername() + " " + this.userDao.getAllUser().get(0).getPassword());

        //Rollback
        updateUser.setPassword("123456");
        this.userDao.updateUser(updateUser);
        System.out.println(this.userDao.getAllUser().get(0).getUsername() + " " + this.userDao.getAllUser().get(0).getPassword());
    }
    static public void main(String[] args){
        SpringApplication.run(Main.class,args);
    }
}
