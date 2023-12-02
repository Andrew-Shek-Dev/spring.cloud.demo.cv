package io.tecky.service;

import io.tecky.SmsClient;
import io.tecky.dao.UserInfoDao;
import io.tecky.dao.UserSmsCodeDao;
import io.tecky.data.UserInfo;
import io.tecky.data.UserSmsCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class UserService {
    @Autowired
    UserSmsCodeDao userSmsCodeDao;

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    SmsClient AwsSnsClient;

    @Autowired
    RedisTemplate redisTemplate;

    public boolean getSmsCode(String mobile_no){
        String smsCode = String.valueOf((int) Math.random() *10000 + 1);
        //TODO: Send SMS Code in free mode
        //https://godleon.github.io/blog/AWS/AWS-CSA-associate-Applications/
        //https://medium.com/%E6%88%91%E6%83%B3%E8%A6%81%E8%AE%8A%E5%BC%B7/aws-saa-compare-sqs-sns-aws-mq-and-kinesis-streams-5388c371a8db
        //https://andy51002000.blogspot.com/2018/09/aws-sqs-vs-sns.html
        //SNS (sender) vs SQS (Receiver)
        System.out.println("SMS Code " + smsCode + " mobile no is " + mobile_no);

        final UserSmsCode sms = UserSmsCode.builder().mobile_no(mobile_no).sms_code(smsCode)/*.send_time(LocalDateTime.now()).create_time(LocalDateTime.now())*/.build();
        userSmsCodeDao.insert(sms);
        return AwsSnsClient.sendVerificationCode(smsCode,mobile_no);
    }

    public String loginByMobile(String mobile_no,String verificationCode){
        final UserSmsCode userWithCode = UserSmsCode.builder().mobile_no(mobile_no).sms_code(verificationCode).build();
        final UserSmsCode selectedUser = userSmsCodeDao.selectByMobileNo(userWithCode);
        if (selectedUser == null){
            return "";
        }
        UserInfo user = UserInfo.builder().mobile_no(mobile_no).build();
        UserInfo selectedUserInfo = userInfoDao.selectByMobile(user);
        if(selectedUserInfo == null){
            selectedUserInfo = UserInfo.builder()
                    .nick_name(selectedUser.getId().toString())
                    .password(String.valueOf((Math.random()*10000) + 1))
                    .user_id(selectedUser.getId().toString())
                    .mobile_no(mobile_no).is_login(1)
                    .login_time(LocalDateTime.now())
                    .create_time(LocalDateTime.now()).build();
            userInfoDao.insert(selectedUserInfo);
        }else{
            selectedUserInfo.setIs_login(1);
            selectedUserInfo.setLogin_time(LocalDateTime.now());
            userInfoDao.update(selectedUserInfo);
        }
        String accessToken = UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
        redisTemplate.opsForValue().set("accessToken",selectedUserInfo,30, TimeUnit.DAYS);
        return accessToken;
    }
}
