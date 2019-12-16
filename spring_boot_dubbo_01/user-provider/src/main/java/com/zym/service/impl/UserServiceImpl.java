package com.zym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zym.entity.User;
import com.zym.api.IUserService;
import org.springframework.stereotype.Component;

/**
 * @Author wangliang
 * @Date 2019/11/19 0019
 * @Description
 */
@Component
@Service()
public class UserServiceImpl implements IUserService {

    @Override
    public User getUserInfo() {
        User user = new User();
        user.setId(1);
        user.setName("小米");
        user.setHobby("科技");
        return user;
    }
}
