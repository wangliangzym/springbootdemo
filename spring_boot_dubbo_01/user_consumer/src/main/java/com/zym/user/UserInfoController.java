package com.zym.user;


import com.alibaba.dubbo.config.annotation.Reference;
import com.zym.api.IUserService;
import com.zym.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author wangliang
 * @Date 2019/11/19 0019
 * @Description
 */
@Controller
@RequestMapping("/")
public class UserInfoController {

    @Reference
    IUserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(){
        return userService.getUserInfo();
    }

}
