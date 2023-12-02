package io.tecky.controller;

import io.tecky.data.ApiResponse;
import io.tecky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "getSmsCode",method = RequestMethod.POST)
    public Boolean getSmsCode(@RequestParam("reqId") String reqId,@RequestParam("mobileNo") String mobileNo){
        return userService.getSmsCode(mobileNo);
    }

    @RequestMapping(value="loginByMobile", method = RequestMethod.POST)
    public ApiResponse loginByMobileNo(@RequestParam("reqId") String reqId, @RequestParam("mobileNo") String mobileNo, @RequestParam("smsCode") String smsCode){
        return ApiResponse.success("200","OK",userService.loginByMobile(mobileNo,smsCode));
    }

    @RequestMapping(value="loginExit",method = RequestMethod.POST)
    public Boolean loginExit(@RequestParam("userId") String userId, @RequestParam("accessToken") String accessToken){
        //TODO
        return true;
    }
}
