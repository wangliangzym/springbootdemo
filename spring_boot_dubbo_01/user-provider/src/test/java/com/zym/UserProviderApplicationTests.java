package com.zym;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zym.api.IUserService;
import com.zym.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserProviderApplicationTests {

    @Test
    void contextLoads() {
    }

    @Reference
    IUserService userService;

    @Test
    public void getUserInfo(){
        User userInfo = userService.getUserInfo();
        System.out.println(userInfo);
    }

}
